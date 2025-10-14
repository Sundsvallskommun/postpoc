package se.sundsvall.postportalservice.service.mapper;

import static java.util.Collections.emptyList;
import static se.sundsvall.postportalservice.Constants.PENDING;
import static se.sundsvall.postportalservice.Constants.UNDELIVERABLE;

import generated.se.sundsvall.citizen.CitizenExtended;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import se.sundsvall.postportalservice.api.model.Address;
import se.sundsvall.postportalservice.api.model.Recipient;
import se.sundsvall.postportalservice.api.model.SmsRecipient;
import se.sundsvall.postportalservice.integration.db.RecipientEntity;
import se.sundsvall.postportalservice.integration.db.converter.MessageType;

@Component
public class EntityMapper {

	public RecipientEntity toRecipientEntity(final SmsRecipient smsRecipient) {
		return Optional.ofNullable(smsRecipient).map(recipient -> RecipientEntity.create()
			.withMessageType(MessageType.SMS)
			.withStatus(PENDING)
			.withPartyId(recipient.getPartyId())
			.withPhoneNumber(recipient.getPhoneNumber()))
			.orElse(null);
	}

	public RecipientEntity toRecipientEntity(final Recipient recipient) {
		if (recipient == null) {
			return null;
		}
		var messageType = switch (recipient.getDeliveryMethod()) {
			case DIGITAL_MAIL -> MessageType.DIGITAL_MAIL;
			case SNAIL_MAIL -> MessageType.SNAIL_MAIL;
			default -> null;
		};

		var recipientEntity = new RecipientEntity();

		if (messageType == MessageType.SNAIL_MAIL) {
			recipientEntity
				.withFirstName(recipient.getAddress().getFirstName())
				.withLastName(recipient.getAddress().getLastName())
				.withStreetAddress(recipient.getAddress().getStreet())
				.withApartmentNumber(recipient.getAddress().getApartmentNumber())
				.withCareOf(recipient.getAddress().getCareOf())
				.withZipCode(recipient.getAddress().getZipCode())
				.withCity(recipient.getAddress().getCity())
				.withCountry(recipient.getAddress().getCountry());
		}

		return recipientEntity
			.withMessageType(messageType)
			.withStatus(PENDING)
			.withPartyId(recipient.getPartyId());
	}

	public RecipientEntity toRecipientEntity(final Address address) {
		return Optional.ofNullable(address).map(address1 -> RecipientEntity.create()
			.withCountry(address1.getCountry())
			.withCity(address1.getCity())
			.withStreetAddress(address1.getStreet())
			.withZipCode(address1.getZipCode())
			.withFirstName(address1.getFirstName())
			.withLastName(address1.getLastName())
			.withApartmentNumber(address1.getApartmentNumber())
			.withCareOf(address1.getCareOf())
			.withMessageType(MessageType.SNAIL_MAIL)
			.withStatus(PENDING))
			.orElse(null);

	}

	public RecipientEntity toDigitalMailRecipientEntity(final String partyId) {
		return Optional.ofNullable(partyId).map(id -> RecipientEntity.create()
			.withPartyId(id)
			.withMessageType(MessageType.DIGITAL_MAIL)
			.withStatus(PENDING))
			.orElse(null);
	}

	public RecipientEntity toSnailMailRecipientEntity(final CitizenExtended citizenExtended) {
		return Optional.ofNullable(citizenExtended).map(citizen -> {
			var address = Optional.ofNullable(citizen.getAddresses()).orElse(emptyList()).stream()
				.filter(citizenAddress -> "Current".equalsIgnoreCase(citizenAddress.getStatus()))
				.findAny()
				.orElse(null);
			if (address == null) {
				return null;
			}

			return RecipientEntity.create()
				.withPartyId(Optional.ofNullable(citizen.getPersonId()).map(UUID::toString).orElse(null))
				.withFirstName(citizen.getGivenname())
				.withLastName(citizen.getLastname())
				.withMessageType(MessageType.SNAIL_MAIL)
				.withStreetAddress(address.getAddress())
				.withApartmentNumber(address.getAppartmentNumber())
				.withCareOf(address.getCo())
				.withZipCode(address.getPostalCode())
				.withCity(address.getCity())
				.withCountry(address.getCountry())
				.withStatus(PENDING);
		})
			.orElse(null);
	}

	public RecipientEntity toUndeliverableRecipientEntity(final CitizenExtended citizenExtended) {
		return Optional.ofNullable(citizenExtended).map(citizen -> RecipientEntity.create()
			.withPartyId(Optional.ofNullable(citizen.getPersonId()).map(UUID::toString).orElse(null))
			.withMessageType(MessageType.LETTER)
			.withStatus(UNDELIVERABLE))
			.orElse(null);

	}
}
