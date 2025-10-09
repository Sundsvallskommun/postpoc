package se.sundsvall.postportalservice.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.dept44.support.Identifier;
import se.sundsvall.postportalservice.Application;
import se.sundsvall.postportalservice.api.model.KivraEligibilityRequest;
import se.sundsvall.postportalservice.api.model.PrecheckRequest;
import se.sundsvall.postportalservice.api.model.PrecheckResponse;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.DeliveryMethod;
import se.sundsvall.postportalservice.api.model.PrecheckResponse.PrecheckRecipient;
import se.sundsvall.postportalservice.service.PrecheckService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class PrecheckResourceTest {

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

		final var precheckResponse = PrecheckResponse.of(List.of(
			new PrecheckRecipient("191111111111", "partyId-1", DeliveryMethod.DIGITAL_MAIL, null),
			new PrecheckRecipient("192222222222", "partyId-2", DeliveryMethod.DIGITAL_MAIL, null)));

		when(precheckService.precheck(MUNICIPALITY_ID, request)).thenReturn(precheckResponse);

		var response = webTestClient.post()
			.uri("/{municipalityId}/precheck", MUNICIPALITY_ID)
			.header(Identifier.HEADER_NAME, "type=adAccount; joe01doe")
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk()
			.expectBody(PrecheckResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.precheckRecipients()).extracting("personalIdentityNumber", "partyId", "deliveryMethod").containsExactlyInAnyOrder(
			tuple("191111111111", "partyId-1", DeliveryMethod.DIGITAL_MAIL),
			tuple("192222222222", "partyId-2", DeliveryMethod.DIGITAL_MAIL));

		verify(precheckService).precheck(MUNICIPALITY_ID, request);
	}

	@Test
	void precheckRecipients_BadRequest() {
		final var request = new PrecheckRequest(List.of("191111111111", "192222222222"));

		webTestClient.post()
			.uri("/{municipalityId}/precheck", INVALID_MUNICIPALITY_ID)
			.header(Identifier.HEADER_NAME, "type=adAccount; joe01doe")
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(precheckService);
	}

	@Test
	void checkKivraEligibility() {
		final var partyId1 = "56652549-4f96-4a8f-94f1-07d581ebbb36";
		final var partyId2 = "da03b33e-9de2-45ac-8291-31a88de59410";
		final var request = new KivraEligibilityRequest().withPartyIds(List.of(partyId1, partyId2));

		when(precheckService.precheckKivra(MUNICIPALITY_ID, request)).thenReturn(List.of(partyId1, partyId2));

		final var response = webTestClient.post()
			.uri("/{municipalityId}/precheck/kivra", MUNICIPALITY_ID)
			.header(Identifier.HEADER_NAME, "type=adAccount; joe01doe")
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk()
			.expectBody(new ParameterizedTypeReference<List<String>>() {
			})
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull().containsExactlyInAnyOrder(partyId1, partyId2);
		verify(precheckService).precheckKivra(MUNICIPALITY_ID, request);
	}

	@Test
	void checkKivraEligibility_BadRequest() {
		final var request = new PrecheckRequest(List.of("191111111111", "192222222222"));

		webTestClient.post()
			.uri("/{municipalityId}/precheck/kivra", INVALID_MUNICIPALITY_ID)
			.header(Identifier.HEADER_NAME, "type=adAccount; joe01doe")
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(precheckService);
	}

}
