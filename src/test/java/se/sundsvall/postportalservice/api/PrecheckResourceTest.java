package se.sundsvall.postportalservice.api;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static se.sundsvall.postportalservice.TestDataFactory.INVALID_MUNICIPALITY_ID;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.postportalservice.Application;
import se.sundsvall.postportalservice.api.model.PrecheckRequest;
import se.sundsvall.postportalservice.api.model.PrecheckResponse;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.DeliveryMethod;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.PrecheckRecipient;
import se.sundsvall.postportalservice.service.PrecheckService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class PrecheckResourceTest {

	private static final String DEPARTMENT_ID = "dept44";

	@MockitoBean
	private PrecheckService precheckService;

	@Autowired
	private WebTestClient webTestClient;

	@AfterEach
	void verifyNoUnexpectedMockInteractions() {
		verifyNoMoreInteractions(precheckService);
	}

	@Test
	void precheck() {
		final var request = new PrecheckRequest(List.of("191111111111", "192222222222"));

		final var response = PrecheckResponse.of(List.of(
			new PrecheckRecipient("191111111111", "partyId-1", DeliveryMethod.DIGITAL_MAIL, null),
			new PrecheckRecipient("192222222222", "partyId-2", DeliveryMethod.DIGITAL_MAIL, null)));

		when(precheckService.precheck(MUNICIPALITY_ID, DEPARTMENT_ID, request.personIds())).thenReturn(response);

		webTestClient.post()
			.uri("/{municipalityId}/{departmentId}/precheck", MUNICIPALITY_ID, DEPARTMENT_ID)
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.recipients.length()").isEqualTo(2);

		verify(precheckService).precheck(eq(MUNICIPALITY_ID), eq(DEPARTMENT_ID), anyList());
	}

	@Test
	void precheckRecipients_BadRequest() {
		final var request = new PrecheckRequest(List.of("191111111111", "192222222222"));

		webTestClient.post()
			.uri("/{municipalityId}/{departmentId}/precheck", INVALID_MUNICIPALITY_ID, DEPARTMENT_ID)
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(precheckService);
	}
}
