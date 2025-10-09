package se.sundsvall.postportalservice.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.zalando.problem.Status.NOT_FOUND;
import static se.sundsvall.postportalservice.TestDataFactory.INVALID_MUNICIPALITY_ID;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import se.sundsvall.postportalservice.Application;
import se.sundsvall.postportalservice.api.model.MessageDetails;
import se.sundsvall.postportalservice.api.model.Messages;
import se.sundsvall.postportalservice.api.model.SigningInformation;
import se.sundsvall.postportalservice.service.HistoryService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class HistoryResourceTest {

	@MockitoBean
	private HistoryService historyService;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getMessageDetails_OK() {
		final var messageId = "12345";
		final var userId = "joe01doe";

		var messageDetails = new MessageDetails();

		when(historyService.getMessageDetails(MUNICIPALITY_ID, userId, messageId))
			.thenReturn(messageDetails);

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages/{messageId}")
				.build(MUNICIPALITY_ID, userId, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK);
	}

	@Test
	void getMessageDetails_NotFound() {
		final var messageId = "12345";
		final var userId = "joe01doe";

		when(historyService.getMessageDetails(MUNICIPALITY_ID, userId, messageId)).thenThrow(Problem.valueOf(NOT_FOUND));

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages/{messageId}")
				.build(MUNICIPALITY_ID, userId, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void getMessageDetails_BadRequest() {
		final var messageId = "12345";
		final var userId = "joe01doe";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages/{messageId}")
				.build(INVALID_MUNICIPALITY_ID, userId, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getUserMessages_OK() {
		final var userId = "12345";
		final var messages = new Messages();
		final var pageableMock = Mockito.mock(Pageable.class);

		when(historyService.getUserMessages(MUNICIPALITY_ID, userId, pageableMock))
			.thenReturn(messages);

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages")
				.build(MUNICIPALITY_ID, userId, pageableMock))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK);
	}

	@Test
	void getUserMessages_BadRequest() {
		final var userId = "12345";
		final var pageableMock = Mockito.mock(Pageable.class);

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages")
				.build(INVALID_MUNICIPALITY_ID, userId, pageableMock))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getSigningInformation() {
		final var messageId = "messageId";

		final var status = "status";
		final var contentKey = "contentKey";
		final var orderReference = "orderReference";
		final var signature = "signature";
		final var oscpResponse = "ocspResponse";
		final var signedAt = OffsetDateTime.now();
		final var result = SigningInformation.create()
			.withStatus(status)
			.withContentKey(contentKey)
			.withOrderReference(orderReference)
			.withSignature(signature)
			.withOcspResponse(oscpResponse)
			.withSignedAt(signedAt);

		when(historyService.getSigningInformation(MUNICIPALITY_ID, messageId)).thenReturn(result);

		var response = webTestClient.get()
			.uri("/{municipalityId}/history/messages/{messageId}/signinginfo", MUNICIPALITY_ID, messageId)
			.exchange()
			.expectBody(SigningInformation.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().satisfies(signingInformation -> {
			assertThat(signingInformation.getStatus()).isEqualTo(status);
			assertThat(signingInformation.getContentKey()).isEqualTo(contentKey);
			assertThat(signingInformation.getOrderReference()).isEqualTo(orderReference);
			assertThat(signingInformation.getSignature()).isEqualTo(signature);
			assertThat(signingInformation.getOcspResponse()).isEqualTo(oscpResponse);
			assertThat(signingInformation.getSignedAt()).isEqualTo(signedAt);
		});

		verify(historyService).getSigningInformation(MUNICIPALITY_ID, messageId);
		verifyNoMoreInteractions(historyService);
	}

	@Test
	void getSigningInformation_badRequest() {
		webTestClient.get()
			.uri("/{municipalityId}/history/messages/{messageId}/signinginfo", INVALID_MUNICIPALITY_ID, "messageId")
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(historyService);
	}

}
