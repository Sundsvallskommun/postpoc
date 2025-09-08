package se.sundsvall.postportalservice.service.mapper;

import java.util.Optional;
import se.sundsvall.postportalservice.api.model.SmsRecipient;
import se.sundsvall.postportalservice.integration.db.RecipientEntity;

public final class EntityMapper {

	private EntityMapper() {}

	public static RecipientEntity toRecipientEntity(final SmsRecipient smsRecipient) {
		return Optional.ofNullable(smsRecipient).map(recipient -> RecipientEntity.create()
			.withPartyId(recipient.getPartyId())
			.withPhoneNumber(recipient.getPhoneNumber()))
			.orElse(null);
	}
}
