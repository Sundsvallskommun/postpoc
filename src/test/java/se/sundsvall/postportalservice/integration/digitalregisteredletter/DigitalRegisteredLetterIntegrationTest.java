package se.sundsvall.postportalservice.integration.digitalregisteredletter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DigitalRegisteredLetterIntegrationTest {

	@Mock
	private DigitalRegisteredLetterClient clientMock;

	@InjectMocks
	private DigitalRegisteredLetterIntegration integration;

	@AfterEach
	void noMoreInteractions() {
		verifyNoMoreInteractions(clientMock);
	}

	@Test
	void checkKivraEligibility_AllEligible() {
		final var partyId1 = "123e4567-e89b-12d3-a456-426614174001";
		final var partyId2 = "123e4567-e89b-12d3-a456-426614174002";
		final var partyId3 = "123e4567-e89b-12d3-a456-426614174003";
		final var partyIds = List.of(partyId1, partyId2, partyId3);
		final var eligibilityRequest = DigitalRegisteredLetterMapper.toEligibilityRequest(partyIds);
		when(clientMock.checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest)).thenReturn(partyIds);

		var result = integration.checkKivraEligibility(MUNICIPALITY_ID, partyIds);

		assertThat(result).hasSameElementsAs(partyIds);
		verify(clientMock).checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest);
	}

	@Test
	void checkKivraEligibility_NoneEligible() {
		final var partyId1 = "123e4567-e89b-12d3-a456-426614174001";
		final var partyId2 = "123e4567-e89b-12d3-a456-426614174002";
		final var partyId3 = "123e4567-e89b-12d3-a456-426614174003";
		final var partyIds = List.of(partyId1, partyId2, partyId3);
		final var eligibilityRequest = DigitalRegisteredLetterMapper.toEligibilityRequest(partyIds);

		final List<String> eligiblePartyIds = Collections.emptyList();
		when(clientMock.checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest)).thenReturn(eligiblePartyIds);

		var result = integration.checkKivraEligibility(MUNICIPALITY_ID, partyIds);

		assertThat(result).isEmpty();
		verify(clientMock).checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest);
	}

	@Test
	void checkKivraEligibility_SomeEligible() {
		final var partyId1 = "123e4567-e89b-12d3-a456-426614174001";
		final var partyId2 = "123e4567-e89b-12d3-a456-426614174002";
		final var partyId3 = "123e4567-e89b-12d3-a456-426614174003";
		final var partyIds = List.of(partyId1, partyId2, partyId3);
		final var eligibilityRequest = DigitalRegisteredLetterMapper.toEligibilityRequest(partyIds);

		final var eligiblePartyIds = List.of(partyId1, partyId2);
		when(clientMock.checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest)).thenReturn(eligiblePartyIds);

		var result = integration.checkKivraEligibility(MUNICIPALITY_ID, partyIds);

		assertThat(result).hasSameElementsAs(eligiblePartyIds);
		verify(clientMock).checkKivraEligibility(MUNICIPALITY_ID, eligibilityRequest);
	}

	@Test
	void getAllLetters() {
		// TODO: Implement when logic is in place.
		var result = integration.getAllLetters(MUNICIPALITY_ID);
		assertThat(result).isTrue();
	}

	@Test
	void getLetterById() {
		// TODO: Implement when logic is in place.
		var result = integration.getLetterById(MUNICIPALITY_ID, "letterId");
		assertThat(result).isTrue();
	}

	@Test
	void sendLetter() {
		// TODO: Implement when logic is in place.
		var result = integration.sendLetter(MUNICIPALITY_ID, null);
		assertThat(result).isTrue();
	}

}
