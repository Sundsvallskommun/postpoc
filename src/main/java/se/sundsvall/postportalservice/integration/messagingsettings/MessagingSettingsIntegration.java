package se.sundsvall.postportalservice.integration.messagingsettings;

import static org.zalando.problem.Status.BAD_GATEWAY;
import static org.zalando.problem.Status.NOT_FOUND;

import generated.se.sundsvall.messagingsettings.SenderInfoResponse;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;

@Component
public class MessagingSettingsIntegration {

	private final MessagingSettingsClient messagingSettingsClient;

	public MessagingSettingsIntegration(final MessagingSettingsClient messagingSettingsClient) {
		this.messagingSettingsClient = messagingSettingsClient;
	}

	public SenderInfoResponse getSenderInfo(final String municipalityId, final String departmentId) {
		return messagingSettingsClient.getSenderInfo(municipalityId, departmentId)
			.stream()
			.findFirst()
			.orElseThrow(() -> Problem.valueOf(BAD_GATEWAY, "Found no sender info for departmentId " + departmentId));
	}

	public String getOrganizationNumber(final String municipalityId, final String departmentId) {
		return messagingSettingsClient.getSenderInfo(municipalityId, departmentId)
			.stream()
			.findFirst()
			.map(SenderInfoResponse::getOrganizationNumber)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "Organization number not found for municipalityId '%s' and departmentId '%s'".formatted(municipalityId, departmentId)));
	}

}
