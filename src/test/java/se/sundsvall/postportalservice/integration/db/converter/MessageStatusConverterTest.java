package se.sundsvall.postportalservice.integration.db.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MessageStatusConverterTest {

	private final MessageStatusConverter messageStatusConverter = new MessageStatusConverter();

	@ParameterizedTest
	@EnumSource(MessageStatus.class)
	void convertToDatabaseColumn(final MessageStatus messageStatus) {
		var result = messageStatusConverter.convertToDatabaseColumn(messageStatus);
		assertThat(result).isEqualTo(messageStatus.toString());
	}

	@ParameterizedTest
	@EnumSource(MessageStatus.class)
	void convertToEntityAttribute(final MessageStatus messageStatus) {
		var result = messageStatusConverter.convertToEntityAttribute(messageStatus.toString());
		assertThat(result).isEqualTo(messageStatus);
	}

}
