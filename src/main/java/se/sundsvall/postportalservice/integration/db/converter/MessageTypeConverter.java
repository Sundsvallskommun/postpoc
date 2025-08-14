package se.sundsvall.postportalservice.integration.db.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class MessageTypeConverter implements AttributeConverter<MessageType, String> {

	@Override
	public String convertToDatabaseColumn(final MessageType attribute) {
		return Optional.ofNullable(attribute).map(MessageType::toString).orElse(null);
	}

	@Override
	public MessageType convertToEntityAttribute(final String dbData) {
		return MessageType.valueOf(dbData);
	}

}
