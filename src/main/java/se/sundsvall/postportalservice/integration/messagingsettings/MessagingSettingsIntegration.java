package se.sundsvall.postportalservice.integration.messagingsettings;

import generated.se.sundsvall.messagingsettings.SenderInfoResponse;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class MessagingSettingsIntegration {

	private final MessagingSettingsClient messagingSettingsClient;

	public MessagingSettingsIntegration(final MessagingSettingsClient messagingSettingsClient) {
		this.messagingSettingsClient = messagingSettingsClient;
	}

	public Optional<String> getSupportText(final String municipalityId, final String departmentId) {
		return messagingSettingsClient.getSenderInfo(municipalityId, departmentId)
			.map(SenderInfoResponse::getSupportText);
	}
}
