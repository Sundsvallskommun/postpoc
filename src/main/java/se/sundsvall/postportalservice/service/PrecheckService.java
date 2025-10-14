package se.sundsvall.postportalservice.service;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import generated.se.sundsvall.citizen.PersonGuidBatch;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import se.sundsvall.postportalservice.api.model.KivraEligibilityRequest;
import se.sundsvall.postportalservice.api.model.PrecheckResponse;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.DeliveryMethod;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.PrecheckRecipient;
import se.sundsvall.postportalservice.integration.citizen.CitizenIntegration;
import se.sundsvall.postportalservice.integration.db.RecipientEntity;
import se.sundsvall.postportalservice.integration.digitalregisteredletter.DigitalRegisteredLetterIntegration;
import se.sundsvall.postportalservice.integration.messaging.MessagingIntegration;
import se.sundsvall.postportalservice.integration.messagingsettings.MessagingSettingsIntegration;
import se.sundsvall.postportalservice.service.mapper.EntityMapper;
import se.sundsvall.postportalservice.service.mapper.PrecheckMapper;
import se.sundsvall.postportalservice.service.util.PrecheckUtil;

@Service
public class PrecheckService {

	public static final String FAILURE_REASON_PARTY_ID_NOT_FOUND = "Party ID not found.";
	public static final String FAILURE_REASON_UNKNOWN_ERROR = "Unknown error.";

	private final DigitalRegisteredLetterIntegration digitalRegisteredLetterIntegration;
	private final CitizenIntegration citizenIntegration;
	private final MessagingSettingsIntegration messagingSettingsIntegration;
	private final MessagingIntegration messagingIntegration;
	private final PrecheckMapper precheckMapper;
	private final EmployeeService employeeService;
	private final EntityMapper entityMapper;

	public PrecheckService(
		final DigitalRegisteredLetterIntegration digitalRegisteredLetterIntegration,
		final CitizenIntegration citizenIntegration,
		final MessagingSettingsIntegration messagingSettingsIntegration,
		final MessagingIntegration messagingIntegration,
		final PrecheckMapper precheckMapper,
		final EmployeeService employeeService,
		final EntityMapper entityMapper) {
		this.digitalRegisteredLetterIntegration = digitalRegisteredLetterIntegration;
		this.citizenIntegration = citizenIntegration;
		this.messagingSettingsIntegration = messagingSettingsIntegration;
		this.messagingIntegration = messagingIntegration;
		this.precheckMapper = precheckMapper;
		this.employeeService = employeeService;
		this.entityMapper = entityMapper;
	}

	public PrecheckResponse precheckPartyIds(final String municipalityId, final List<String> partyIds) {
		final var sentBy = employeeService.getSentBy(municipalityId);
		final var orgNumber = messagingSettingsIntegration.getOrganizationNumber(municipalityId, sentBy.departmentId());

		// Checks digital mailbox eligibility for all provided partyIds
		final var mailboxes = messagingIntegration.precheckMailboxes(municipalityId, orgNumber, partyIds);

		// Find those that can be reached by digital mail
		final var reachableByDigitalMail = PrecheckUtil.filterReachableMailboxes(mailboxes);
		// Create recipients for those that can be reached by digital mail
		final var digitalMailRecipients = reachableByDigitalMail.stream()
			.map(partyId -> new PrecheckRecipient(null, partyId, DeliveryMethod.DIGITAL_MAIL, null));

		// Find those that cannot be reached by digital mail
		final var unreachableByDigitalMail = PrecheckUtil.filterUnreachableMailboxes(mailboxes);
		// Retrieve citizen data for those that cannot be reached by digital mail
		final var citizens = citizenIntegration.getCitizens(municipalityId, unreachableByDigitalMail);
		// Find those that are eligible for snail mail and create recipients for them
		final var snailMailRecipients = precheckMapper.toSnailMailEligiblePartyIds(citizens, citizenIntegration::isRegisteredInSweden).stream()
			.map(partyId -> new PrecheckRecipient(null, partyId, DeliveryMethod.SNAIL_MAIL, null));

		// Find those citizens that are ineligible for both digital and snail mail and create recipients for them
		final var ineligibleForBoth = precheckMapper.toSnailMailIneligiblePartyIds(citizens, citizenIntegration::isRegisteredInSweden).stream()
			.map(partyId -> new PrecheckRecipient(null, partyId, DeliveryMethod.DELIVERY_NOT_POSSIBLE, null));

		// Combine all recipient streams into a single list
		var recipients = Stream.of(digitalMailRecipients, snailMailRecipients, ineligibleForBoth)
			.flatMap(Function.identity())
			.toList();

		return PrecheckResponse.of(recipients);
	}

	public List<RecipientEntity> precheckLegalIds(final String municipalityId, final List<String> legalIds) {
		if (legalIds == null || legalIds.isEmpty()) {
			return emptyList();
		}

		final var batches = citizenIntegration.getPartyIds(municipalityId, legalIds);

		final var partyIds = Optional.ofNullable(batches).orElse(emptyList()).stream()
			.filter(batch -> Boolean.TRUE.equals(batch.getSuccess()))
			.map(PersonGuidBatch::getPersonId)
			.filter(Objects::nonNull)
			.map(UUID::toString)
			.toList();

		final var sentBy = employeeService.getSentBy(municipalityId);
		final var orgNumber = messagingSettingsIntegration.getOrganizationNumber(municipalityId, sentBy.departmentId());
		final var mailboxes = messagingIntegration.precheckMailboxes(municipalityId, orgNumber, partyIds);
		final var reachable = PrecheckUtil.filterReachableMailboxes(mailboxes);

		// RecipientEntities for those citizens that are eligible for digital mail
		final var digitalMailRecipients = ofNullable(reachable).orElse(emptyList()).stream()
			.map(entityMapper::toDigitalMailRecipientEntity)
			.filter(Objects::nonNull);

		final var unreachable = PrecheckUtil.filterUnreachableMailboxes(mailboxes);
		final var citizens = citizenIntegration.getCitizens(municipalityId, unreachable);

		// RecipientEntities for those citizens that are eligible for snail mail
		final var snailMailRecipients = ofNullable(citizens).orElse(emptyList()).stream()
			.filter(citizenIntegration::isRegisteredInSweden)
			.map(entityMapper::toSnailMailRecipientEntity)
			.filter(Objects::nonNull);

		// RecipientEntities for those citizens that are ineligible for both digital and snail mail
		final var undeliverableRecipients = ofNullable(citizens).orElse(emptyList()).stream()
			.filter(citizen -> !citizenIntegration.isRegisteredInSweden(citizen))
			.map(entityMapper::toUndeliverableRecipientEntity)
			.filter(Objects::nonNull);

		// Combine all recipient streams into a single list
		return Stream.of(digitalMailRecipients, snailMailRecipients, undeliverableRecipients)
			.flatMap(Function.identity())
			.toList();
	}

	public List<String> precheckKivra(final String municipalityId, final KivraEligibilityRequest request) {
		return digitalRegisteredLetterIntegration.checkKivraEligibility(municipalityId, request.getPartyIds());
	}
}
