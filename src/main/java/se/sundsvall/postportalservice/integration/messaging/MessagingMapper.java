package se.sundsvall.postportalservice.integration.messaging;

import generated.se.sundsvall.messaging.DigitalMailRequest;
import generated.se.sundsvall.messaging.SmsBatchRequest;
import generated.se.sundsvall.messaging.SmsRequest;

public final class MessagingMapper {

	private MessagingMapper() {}

	public static SmsRequest toSmsRequest() {
		return new SmsRequest();
	}

	public static SmsBatchRequest toSmsBatchRequest() {
		return new SmsBatchRequest();
	}

	public static DigitalMailRequest toDigitalMailRequest() {
		return new DigitalMailRequest();
	}

}
