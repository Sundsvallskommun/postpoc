package se.sundsvall.postportalservice.integration.messaging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessagingIntegrationTest {

	@Mock
	private MessagingClient messagingClientMock;

	@InjectMocks
	private MessagingIntegration messagingIntegration;

	@AfterEach
	void verifyInteractions() {
		verifyNoMoreInteractions(messagingClientMock);
	}

	@Test
	void sendDigitalMail() {
		assertThat(messagingIntegration.sendDigitalMail(MUNICIPALITY_ID)).isTrue();
	}

	@Test
	void sendSms() {
		assertThat(messagingIntegration.sendSms(MUNICIPALITY_ID)).isTrue();
	}

	@Test
	void sendSmsBatch() {
		assertThat(messagingIntegration.sendSmsBatch(MUNICIPALITY_ID)).isTrue();
	}

	@Test
	void sendSnailMail() {
		assertThat(messagingIntegration.sendSnailMail(MUNICIPALITY_ID)).isTrue();
	}

	@Test
	void precheckMailBoxes() {
		assertThat(messagingIntegration.precheckMailBoxes(MUNICIPALITY_ID)).isTrue();
	}

}
