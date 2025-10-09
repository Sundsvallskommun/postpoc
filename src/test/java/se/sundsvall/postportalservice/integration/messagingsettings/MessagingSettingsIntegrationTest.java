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
	private MessagingSettingsClient clientMock;

	@InjectMocks
	private MessagingSettingsIntegration integration;

	@AfterEach
	void noMoreInteractions() {
		verifyNoMoreInteractions(clientMock);
	}

	@Test
	void getSupportText() {
		final var supportText = "foo bar baz";
		final var senderInfo = new SenderInfoResponse();

		senderInfo.setSupportText(supportText);

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isNotEmpty();
		assertThat(result.get()).isEqualTo(supportText);
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSupportText_withNoMatch() {
		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(emptyList());

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSupportText_withNullSupportText() {
		final var senderInfo = new SenderInfoResponse();

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSenderInfo() {
		final var senderInfo = new SenderInfoResponse();

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		var result = integration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(senderInfo);
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSenderInfo_withNoMatch() {
		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(emptyList());

		assertThatThrownBy(() -> integration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Bad Gateway: Found no sender info for departmentId " + DEPARTMENT_ID);

		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber() {
		final var organizationNumber = "162021005489";
		final var senderInfo = new SenderInfoResponse();

		senderInfo.setOrganizationNumber(organizationNumber);

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		final var result = integration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).contains(organizationNumber);
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber_withNoMatch() {
		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(emptyList());

		final var result = integration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getOrganizationNumber_withNullValue() {
		final var senderInfo = new SenderInfoResponse();

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(List.of(senderInfo));

		final var result = integration.getOrganizationNumber(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSenderInfo_picksFirstWhenMultiple() {
		final var first = new SenderInfoResponse();
		first.setSupportText("first");
		first.setOrganizationNumber("1111111111");

		final var second = new SenderInfoResponse();
		second.setSupportText("second");
		second.setOrganizationNumber("2222222222");

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID))
			.thenReturn(List.of(first, second));

		final var result = integration.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result.getSupportText()).isEqualTo("first");
		assertThat(result.getOrganizationNumber()).isEqualTo("1111111111");
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}
}
