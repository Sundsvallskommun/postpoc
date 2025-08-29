package se.sundsvall.postportalservice.integration.digitalregisteredletter;

import generated.se.sundsvall.digitalregisteredletter.EligibilityRequest;
import java.util.List;

public final class DigitalRegisteredLetterMapper {

	private DigitalRegisteredLetterMapper() {}

	public static EligibilityRequest toEligibilityRequest(final List<String> partyIds) {
		return new EligibilityRequest().partyIds(partyIds);
	}

}
