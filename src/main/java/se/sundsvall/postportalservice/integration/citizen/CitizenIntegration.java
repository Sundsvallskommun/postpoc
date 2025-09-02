package se.sundsvall.postportalservice.integration.citizen;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.zalando.problem.Status.NOT_FOUND;

import generated.se.sundsvall.citizen.CitizenAddress;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;

@Component
public class CitizenIntegration {

	static final String POPULATION_REGISTRATION_ADDRESS = "POPULATION_REGISTRATION_ADDRESS";

	private final CitizenClient client;

	public CitizenIntegration(final CitizenClient client) {
		this.client = client;
	}

	public CitizenAddress getCitizenAddress(final String municipalityId, final String partyId) {
		final var citizen = client.getCitizen(municipalityId, partyId)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "No citizen found."));

		return ofNullable(citizen.getAddresses())
			.orElse(emptyList())
			.stream()
			.filter(address -> POPULATION_REGISTRATION_ADDRESS.equals(address.getAddressType()))
			.findFirst()
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "No citizen address found."));
	}
}
