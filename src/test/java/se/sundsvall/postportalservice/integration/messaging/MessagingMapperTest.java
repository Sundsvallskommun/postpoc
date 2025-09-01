package se.sundsvall.postportalservice.integration.messaging;

import static org.assertj.core.api.Assertions.assertThat;

import generated.se.sundsvall.messaging.DigitalMailRequest;
import generated.se.sundsvall.messaging.SmsBatchRequest;
import generated.se.sundsvall.messaging.SmsRequest;
import org.junit.jupiter.api.Test;

class MessagingMapperTest {

	@Test
	void toSmsRequest() {
		var result = MessagingMapper.toSmsRequest();
		assertThat(result).isInstanceOf(SmsRequest.class);
	}

	@Test
	void toSmsBatchRequest() {
		var result = MessagingMapper.toSmsBatchRequest();
		assertThat(result).isInstanceOf(SmsBatchRequest.class);
	}

	@Test
	void toDigitalMailRequest() {
		var result = MessagingMapper.toDigitalMailRequest();
		assertThat(result).isInstanceOf(DigitalMailRequest.class);
	}
}
