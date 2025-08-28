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
import se.sundsvall.postportalservice.api.model.DigitalRegisteredLetterRequest;
import se.sundsvall.postportalservice.api.model.LetterRequest;
import se.sundsvall.postportalservice.api.model.SmsRequest;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class MessageResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void sendLetter_OK() {
		final var userId = "12345";
		final var messageId = "12345";

		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/letter")
				.build(MUNICIPALITY_ID, userId, messageId))
			.bodyValue(new LetterRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void sendLetter_BadRequest() {
		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/letter")
				.build(INVALID_MUNICIPALITY_ID))
			.bodyValue(new LetterRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void sendDigitalRegisteredLetter_OK() {
		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/registered-letter")
				.build(MUNICIPALITY_ID))
			.bodyValue(new DigitalRegisteredLetterRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void sendDigitalRegisteredLetter_BadRequest() {
		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/registered-letter")
				.build(INVALID_MUNICIPALITY_ID))
			.bodyValue(new DigitalRegisteredLetterRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void sendSms_OK() {
		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/sms")
				.build(MUNICIPALITY_ID))
			.bodyValue(new SmsRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void sendSms_BadRequest() {
		webTestClient.post()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/messages/sms")
				.build(INVALID_MUNICIPALITY_ID))
			.bodyValue(new SmsRequest())
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}
}
