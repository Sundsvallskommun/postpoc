package se.sundsvall.postportalservice.integration.citizen;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.zalando.problem.Status.NOT_FOUND;

import generated.se.sundsvall.citizen.CitizenAddress;
import generated.se.sundsvall.citizen.CitizenExtended;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.ThrowableProblem;

@ExtendWith(MockitoExtension.class)
class CitizenIntegrationTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final List<String> PARTY_IDS = List.of(
		"28fba79e-73aa-4ecb-939f-301f326d2d4c",
		"f560865a-51f0-4e96-bca1-55d57a0d3f68");

	@Mock
	private CitizenClient citizenClientMock;

	@InjectMocks
	private CitizenIntegration citizenIntegration;

	@AfterEach
	void verifyInteractions() {
		verifyNoMoreInteractions(citizenClientMock);
	}

	@Test
	void getCitizens() {
		final var citizen1 = createCitizen(emptyList());
		final var citizen2 = createCitizen(emptyList());
		final var citizens = List.of(citizen1, citizen2);

		when(citizenClientMock.getCitizens(MUNICIPALITY_ID, PARTY_IDS)).thenReturn(citizens);

		final var result = citizenIntegration.getCitizens(MUNICIPALITY_ID, PARTY_IDS);

		assertThat(result).hasSize(2);
		assertThat(result).containsExactly(citizen1, citizen2);
		verify(citizenClientMock).getCitizens(MUNICIPALITY_ID, PARTY_IDS);
	}

	@Test
	void getCitizens_throwsIfNoData() {
		when(citizenClientMock.getCitizens(MUNICIPALITY_ID, PARTY_IDS)).thenReturn(null);

		assertThatExceptionOfType(ThrowableProblem.class)
			.isThrownBy(() -> citizenIntegration.getCitizens(MUNICIPALITY_ID, PARTY_IDS))
			.satisfies(problem -> {
				assertThat(problem.getStatus()).isEqualTo(NOT_FOUND);
				assertThat(problem.getDetail()).isEqualTo("No citizen data found.");
			});

		verify(citizenClientMock).getCitizens(MUNICIPALITY_ID, PARTY_IDS);
	}

	@Test
	void getPopulationRegistrationAddress_emptyIfNoCitizen() {
		final var result = citizenIntegration.getPopulationRegistrationAddress(null);

		assertThat(result).isEmpty();
	}

	@Test
	void getPopulationRegistrationAddress_emptyIfNoAddresses() {
		final var citizen = createCitizen(emptyList());

		final var result = citizenIntegration.getPopulationRegistrationAddress(citizen);

		assertThat(result).isEmpty();
	}

	@Test
	void getPopulationRegistrationAddress_emptyIfNoMatchingType() {
		final var citizen = createCitizen(List.of(createAddress("SOME_ADDRESS_TYPE"), createAddress("FOREIGN_ADDRESS")));

		final var result = citizenIntegration.getPopulationRegistrationAddress(citizen);

		assertThat(result).isEmpty();
	}

	@Test
	void getPopulationRegistrationAddress_firstMatching() {
		final var address1 = createAddress("SOME_ADDRESS_TYPE");
		final var address2 = createAddress(CitizenIntegration.POPULATION_REGISTRATION_ADDRESS);
		final var address3 = createAddress(CitizenIntegration.POPULATION_REGISTRATION_ADDRESS);
		final var citizen = createCitizen(List.of(address1, address2, address3));

		final var result = citizenIntegration.getPopulationRegistrationAddress(citizen);

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(address2);
	}

	private CitizenAddress createAddress(String type) {
		var address = new CitizenAddress();
		address.setAddressType(type);
		return address;
	}

	private CitizenExtended createCitizen(List<CitizenAddress> addresses) {
		var citizen = new CitizenExtended();
		citizen.setAddresses(addresses);
		return citizen;
	}
}
