package se.sundsvall.postportalservice.service;

import org.springframework.stereotype.Service;
import se.sundsvall.postportalservice.api.model.SmsRequest;
import se.sundsvall.postportalservice.integration.messaging.MessagingIntegration;
import se.sundsvall.postportalservice.service.mapper.EntityMapper;

@Service
public class MessageService {

	private final MessagingIntegration messagingIntegration;

	public MessageService(final MessagingIntegration messagingIntegration) {
		this.messagingIntegration = messagingIntegration;
	}

	public String sendSms(final String municipalityId, final SmsRequest smsRequest) {
		var recipients = smsRequest.getRecipients().stream()
			.map(EntityMapper::toRecipientEntity)
			.toList();

		messagingIntegration.sendSms(municipalityId);

		return "message-id";
	}

}
