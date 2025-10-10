package se.sundsvall.postportalservice.service.mapper;

import static java.lang.Boolean.FALSE;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static se.sundsvall.postportalservice.service.PrecheckService.FAILURE_REASON_PARTY_ID_NOT_FOUND;
import static se.sundsvall.postportalservice.service.PrecheckService.FAILURE_REASON_UNKNOWN_ERROR;

import generated.se.sundsvall.citizen.CitizenExtended;
import generated.se.sundsvall.citizen.PersonGuidBatch;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.DeliveryMethod;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.PrecheckRecipient;

@Component
public final class PrecheckMapper {

	public Map<String, String> toFailureByPersonId(final List<PersonGuidBatch> batches) {
		return ofNullable(batches)
			.orElse(emptyList())
			.stream()
			.filter(personGuidBatch -> FALSE.equals(personGuidBatch.getSuccess()))
			.collect(LinkedHashMap::new,
				(map, personGuidBatch) -> map.put(
					personGuidBatch.getPersonNumber(),
					ofNullable(personGuidBatch.getErrorMessage())
						.filter(String::isBlank)
						.filter(errorMessage -> !errorMessage.isEmpty())
						.orElse(FAILURE_REASON_UNKNOWN_ERROR)),
				Map::putAll);
	}

	public List<PrecheckRecipient> toRecipientsWithoutPartyIds(final List<String> personIds, final Map<String, String> failureByPersonId) {
		return ofNullable(personIds)
			.orElse(emptyList())
			.stream()
			.map(personId -> {
				final var failure = failureByPersonId.get(personId);
				final var reason = failure != null
					? failure
					: FAILURE_REASON_PARTY_ID_NOT_FOUND;

				return new PrecheckRecipient(personId, null, DeliveryMethod.DELIVERY_NOT_POSSIBLE, reason);
			}).toList();
	}

	public List<String> toSnailMailEligiblePartyIds(final List<CitizenExtended> citizens, final Predicate<CitizenExtended> isEligible) {
		return ofNullable(citizens).orElse(emptyList())
			.stream()
			.filter(isEligible)
			.map(CitizenExtended::getPersonId)
			.filter(Objects::nonNull)
			.map(UUID::toString)
			.toList();
	}

	public List<String> toSnailMailIneligiblePartyIds(final List<CitizenExtended> citizens, final Predicate<CitizenExtended> isEligible) {
		return ofNullable(citizens).orElse(emptyList())
			.stream()
			.filter(isEligible.negate())
			.map(CitizenExtended::getPersonId)
			.filter(Objects::nonNull)
			.map(UUID::toString)
			.toList();
	}

	public Map<String, String> mapPersonIdToPartyId(final List<PersonGuidBatch> personGuidBatches) {
		return ofNullable(personGuidBatches).orElse(emptyList())
			.stream()
			.collect(LinkedHashMap::new,
				(map, personGuidBatch) -> map.put(personGuidBatch.getPersonNumber(), personGuidBatch.getPersonId() == null
					? null
					: personGuidBatch.getPersonId().toString()),
				Map::putAll);
	}

}
