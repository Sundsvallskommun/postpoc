package se.sundsvall.postportalservice.integration.citizen;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.zalando.problem.Status.NOT_FOUND;

import generated.se.sundsvall.citizen.CitizenAddress;
import generated.se.sundsvall.citizen.CitizenExtended;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;

@Component
public class CitizenIntegration {

	static final String POPULATION_REGISTRATION_ADDRESS = "POPULATION_REGISTRATION_ADDRESS";

	private final CitizenClient client;

	public CitizenIntegration(final CitizenClient client) {
		this.client = client;
	}

	public List<CitizenExtended> getCitizens(final String municipalityId, final List<String> partyIds) {
		return ofNullable(client.getCitizens(municipalityId, partyIds))
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "No citizen data found."));
	}

	public Optional<CitizenAddress> getPopulationRegistrationAddress(final CitizenExtended citizen) {
		if (citizen == null) {
			return Optional.empty();
		}

		return ofNullable(citizen.getAddresses())
			.orElse(emptyList())
			.stream()
			.filter(address -> POPULATION_REGISTRATION_ADDRESS.equals(address.getAddressType()))
			.findFirst();
	}
}
