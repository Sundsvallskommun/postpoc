package se.sundsvall.postportalservice.integration.digitalregisteredletter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class DigitalRegisteredLetterMapperTest {

	@Test
	void toEligibilityRequest() {
		final var partyId1 = "123e4567-e89b-12d3-a456-426614174001";
		final var partyId2 = "123e4567-e89b-12d3-a456-426614174002";
		final var partyId3 = "123e4567-e89b-12d3-a456-426614174003";
		final var partyIds = List.of(partyId1, partyId2, partyId3);

		var result = DigitalRegisteredLetterMapper.toEligibilityRequest(partyIds);

		assertThat(result.getPartyIds()).containsExactlyInAnyOrder(partyId1, partyId2, partyId3);
	}
}
