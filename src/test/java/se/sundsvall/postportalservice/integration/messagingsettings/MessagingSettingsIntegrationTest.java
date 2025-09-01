package se.sundsvall.postportalservice.integration.messagingsettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import generated.se.sundsvall.messagingsettings.SenderInfoResponse;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(Optional.of(senderInfo));

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isNotEmpty();
		assertThat(result.get()).isEqualTo(supportText);
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSupportText_withNoMatch() {
		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(Optional.empty());

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}

	@Test
	void getSupportText_withNullSupportText() {
		final var senderInfo = new SenderInfoResponse();

		when(clientMock.getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID)).thenReturn(Optional.of(senderInfo));

		final var result = integration.getSupportText(MUNICIPALITY_ID, DEPARTMENT_ID);

		assertThat(result).isEmpty();
		verify(clientMock).getSenderInfo(MUNICIPALITY_ID, DEPARTMENT_ID);
	}
}
