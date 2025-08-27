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
class AttachmentResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getAttachmentsByMessageId_OK() {
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/attachments/messages/{messageId}")
				.build(MUNICIPALITY_ID, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getAttachmentsByMessageId_BadRequest() {
		final var messageId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/attachments/messages/{messageId}")
				.build(INVALID_MUNICIPALITY_ID, messageId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getAttachmentsByMessageId_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void streamAttachment() {
		final var attachmentId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/attachments/{attachmentId}")
				.build(MUNICIPALITY_ID, attachmentId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void streamAttachment_BadRequest() {
		final var attachmentId = "12345";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/attachments/{attachmentId}")
				.build(INVALID_MUNICIPALITY_ID, attachmentId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
