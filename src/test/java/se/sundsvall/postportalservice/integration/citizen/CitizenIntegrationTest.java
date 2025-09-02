package se.sundsvall.postportalservice.integration.citizen;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.zalando.problem.Status.NOT_FOUND;

import generated.se.sundsvall.citizen.CitizenAddress;
import generated.se.sundsvall.citizen.CitizenExtended;
import java.util.List;
import java.util.Optional;
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
	private static final String PARTY_ID = "28fba79e-73aa-4ecb-939f-301f326d2d4c";

	@Mock
	private CitizenClient citizenClientMock;

	@InjectMocks
	private CitizenIntegration citizenIntegration;

	@AfterEach
	void verifyInteractions() {
		verifyNoMoreInteractions(citizenClientMock);
	}

	@Test
	void getCitizenAddress() {
		final var citizen = new CitizenExtended();
		final var citizenAddress = new CitizenAddress();

		citizenAddress.setAddressType(CitizenIntegration.POPULATION_REGISTRATION_ADDRESS);
		citizen.setAddresses(List.of(citizenAddress));

		when(citizenClientMock.getCitizen(MUNICIPALITY_ID, PARTY_ID)).thenReturn(Optional.of(citizen));

		final var result = citizenIntegration.getCitizenAddress(MUNICIPALITY_ID, PARTY_ID);

		assertThat(result).isEqualTo(citizenAddress);
		verify(citizenClientMock).getCitizen(MUNICIPALITY_ID, PARTY_ID);
	}

	@Test
	void getCitizenAddress_throwsIfNotFound() {
		when(citizenClientMock.getCitizen(MUNICIPALITY_ID, PARTY_ID)).thenReturn(Optional.empty());

		assertThatExceptionOfType(ThrowableProblem.class)
			.isThrownBy(() -> citizenIntegration.getCitizenAddress(MUNICIPALITY_ID, PARTY_ID))
			.satisfies(problem -> {
				assertThat(problem.getStatus()).isEqualTo(NOT_FOUND);
				assertThat(problem.getDetail()).isEqualTo("No citizen found.");
			});

		verify(citizenClientMock).getCitizen(MUNICIPALITY_ID, PARTY_ID);
	}

	@Test
	void getCitizenAddress_throwsIfNoAddress() {
		final var citizen = new CitizenExtended();

		when(citizenClientMock.getCitizen(MUNICIPALITY_ID, PARTY_ID)).thenReturn(Optional.of(citizen));

		assertThatExceptionOfType(ThrowableProblem.class)
			.isThrownBy(() -> citizenIntegration.getCitizenAddress(MUNICIPALITY_ID, PARTY_ID))
			.satisfies(problem -> {
				assertThat(problem.getStatus()).isEqualTo(NOT_FOUND);
				assertThat(problem.getDetail()).isEqualTo("No citizen address found.");
			});

		verify(citizenClientMock).getCitizen(MUNICIPALITY_ID, PARTY_ID);
	}

	@Test
	void getCitizenAddress_throwsIfNoPopulationRegistrationAddress() {
		final var citizen = new CitizenExtended();
		final var citizenAddress = new CitizenAddress();

		citizenAddress.setAddressType("FOREIGN_ADDRESS");
		citizen.setAddresses(List.of(citizenAddress));

		when(citizenClientMock.getCitizen(MUNICIPALITY_ID, PARTY_ID)).thenReturn(Optional.of(citizen));

		assertThatExceptionOfType(ThrowableProblem.class)
			.isThrownBy(() -> citizenIntegration.getCitizenAddress(MUNICIPALITY_ID, PARTY_ID))
			.satisfies(problem -> {
				assertThat(problem.getStatus()).isEqualTo(NOT_FOUND);
				assertThat(problem.getDetail()).isEqualTo("No citizen address found.");
			});

		verify(citizenClientMock).getCitizen(MUNICIPALITY_ID, PARTY_ID);
	}
}
