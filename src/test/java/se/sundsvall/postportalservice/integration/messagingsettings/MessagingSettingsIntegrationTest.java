package se.sundsvall.postportalservice.integration.messagingsettings;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import generated.se.sundsvall.messagingsettings.SenderInfoResponse;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Problem;

@ExtendWith(MockitoExtension.class)
class MessagingSettingsIntegrationTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String DEPARTMENT_ID = "dept44";

	@Mock
	private MessagingSettingsClient messagingSettingsClient;

	@InjectMocks
	private MessagingSettingsIntegration messagingSettingsIntegration;

	@AfterEach
	void noMoreInteractions() {
		verifyNoMoreInteractions(messagingSettingsClient);
	}

	@Test
	void getSenderInfo() {
		final var senderInfo = new SenderInfoResponse();

		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		var result = messagingSettingsIntegration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(senderInfo);
		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSenderInfo_withNoMatch() {
		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(emptyList());

		assertThatThrownBy(() -> messagingSettingsIntegration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Bad Gateway: Found no sender info for departmentId " + DEPARTMENT_ID);

		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber() {
		final var organizationNumber = "162021005489";
		final var senderInfo = new SenderInfoResponse();

		senderInfo.setOrganizationNumber(organizationNumber);

		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		final var result = messagingSettingsIntegration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).contains(organizationNumber);
		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber_withNoMatch() {
		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(emptyList());

		assertThatThrownBy(() -> messagingSettingsIntegration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Not Found: Organization number not found for municipalityId '%s' and departmentId '%s'".formatted(MUNICIPALITY_ID, DEPARTMENT_ID));

		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber_withNullValue() {
		final var senderInfo = new SenderInfoResponse();

		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		assertThatThrownBy(() -> messagingSettingsIntegration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Not Found: Organization number not found for municipalityId '%s' and departmentId '%s'".formatted(MUNICIPALITY_ID, DEPARTMENT_ID));

		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSenderInfo_picksFirstWhenMultiple() {
		final var first = new SenderInfoResponse();
		first.setSupportText("first");
		first.setOrganizationNumber("1111111111");

		final var second = new SenderInfoResponse();
		second.setSupportText("second");
		second.setOrganizationNumber("2222222222");

		when(messagingSettingsClient.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID))
			.thenReturn(List.of(first, second));

		final var result = messagingSettingsIntegration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result.getSupportText()).isEqualTo("first");
		assertThat(result.getOrganizationNumber()).isEqualTo("1111111111");
		verify(messagingSettingsClient).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}
}
