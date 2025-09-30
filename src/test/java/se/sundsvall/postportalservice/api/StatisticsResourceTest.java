package se.sundsvall.postportalservice.api;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static se.sundsvall.postportalservice.TestDataFactory.INVALID_MUNICIPALITY_ID;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.postportalservice.Application;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class StatisticsResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getStatisticsByDepartment_OK() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments")
				.queryParam("year", "2025")
				.queryParam("month", "12")
				.build(MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	void getStatisticsByDepartment_BadRequest() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments")
				.build(INVALID_MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isBadRequest();
	}

}
