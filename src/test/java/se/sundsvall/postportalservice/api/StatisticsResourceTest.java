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
class StatisticsResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getStatisticsByDepartment_OK() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments")
				.build(MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getStatisticsByDepartment_BadRequest() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments")
				.build(INVALID_MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getDepartmentStatistics_OK() {
		final var departmentId = "departmentId";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments/{departmentId}")
				.build(MUNICIPALITY_ID, departmentId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getDepartmentStatistics_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void getDepartmentStatistics_BadRequest() {
		final var departmentId = "departmentId";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/departments/{departmentId}")
				.build(INVALID_MUNICIPALITY_ID, departmentId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getStatisticsByUser_OK() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/users")
				.build(MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getStatisticsByUser_BadRequest() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/users")
				.build(INVALID_MUNICIPALITY_ID))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getStatisticsByUser_NotFound() {
		// TODO: Implement when functionality is in place
	}

	@Test
	void getUserStatistics_OK() {
		final var userId = "userId";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/users/{userId}")
				.build(MUNICIPALITY_ID, userId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
	}

	@Test
	void getUserStatistics_BadRequest() {
		final var userId = "userId";

		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.replacePath("/{municipalityId}/statistics/users/{userId}")
				.build(INVALID_MUNICIPALITY_ID, userId))
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void getUserStatistics_NotFound() {
		// TODO: Implement when functionality is in place
	}

}
