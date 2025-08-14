package se.sundsvall.postportalservice.integration.db.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class MessageStatusConverter implements AttributeConverter<MessageStatus, String> {

	@Override
	public String convertToDatabaseColumn(final MessageStatus attribute) {
		return Optional.ofNullable(attribute).map(MessageStatus::toString).orElse(null);
	}

	@Override
	public MessageStatus convertToEntityAttribute(final String dbData) {
		return MessageStatus.valueOf(dbData);
	}

}
