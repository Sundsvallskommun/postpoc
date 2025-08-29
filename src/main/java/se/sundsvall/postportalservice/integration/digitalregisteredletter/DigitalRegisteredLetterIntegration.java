package se.sundsvall.postportalservice.integration.digitalregisteredletter;

import static se.sundsvall.postportalservice.integration.digitalregisteredletter.DigitalRegisteredLetterMapper.toEligibilityRequest;

import java.util.List;
import org.springframework.stereotype.Component;
import se.sundsvall.postportalservice.api.model.DigitalRegisteredLetterRequest;

@Component
public class DigitalRegisteredLetterIntegration {

	private final DigitalRegisteredLetterClient client;

	public DigitalRegisteredLetterIntegration(final DigitalRegisteredLetterClient client) {
		this.client = client;
	}

	/**
	 * Takes a list of partyIds and checks their Kivra eligibility, returning a list of eligible partyIds.
	 *
	 * @param  municipalityId the municipality id
	 * @param  partyIds       the party ids to check
	 * @return                a list of eligible partyIds
	 */
	public List<String> checkKivraEligibility(final String municipalityId, final List<String> partyIds) {
		final var request = toEligibilityRequest(partyIds);
		return client.checkKivraEligibility(municipalityId, request);
	}

	public boolean getAllLetters(final String municipalityId) {
		// TODO: Figure out how this should be implemented, this returns a paginated list of all letters.
		// Not just the letters sent by PostPortalService..
		return true;
	}

	public boolean getLetterById(final String municipalityId, final String letterId) {
		// Used to fetch status for a specific letter.
		// TODO: Implement, this should update the status of the requested letter in the postportalservice
		return true;
	}

	public boolean sendLetter(final String municipalityId, final DigitalRegisteredLetterRequest request) {
		// TODO: map the request to match the expected format in the DigitalRegisteredLetterClient
		return true;
	}

}
