package se.sundsvall.postportalservice.api;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static se.sundsvall.postportalservice.TestDataFactory.INVALID_MUNICIPALITY_ID;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.postportalservice.Application;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class HistoryResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getMessageById_OK() {
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/messages/{messageId}")
				.build(MUNICIPALITY_ID, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getMessageById_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void getMessageById_BadRequest() {
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/messages/{messageId}")
				.build(INVALID_MUNICIPALITY_ID, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getUserMessages_OK() {
		final var userId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages")
				.build(MUNICIPALITY_ID, userId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getUserMessages_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void getUserMessages_BadRequest() {
		final var userId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages")
				.build(INVALID_MUNICIPALITY_ID, userId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getUserMessage_OK() {
		final var userId = "12345";
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages/{messageId}")
				.build(MUNICIPALITY_ID, userId, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getUserMessage_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void getUserMessage_BadRequest() {
		final var userId = "12345";
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/history/users/{userId}/messages/{messageId}")
				.build(INVALID_MUNICIPALITY_ID, userId, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
