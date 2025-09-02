package se.sundsvall.postportalservice.integration.citizen;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static se.sundsvall.postportalservice.integration.citizen.configuration.CitizenConfiguration.CLIENT_ID;

import generated.se.sundsvall.citizen.CitizenExtended;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.sundsvall.postportalservice.integration.citizen.configuration.CitizenConfiguration;

@CircuitBreaker(name = CLIENT_ID)
@FeignClient(
	name = CLIENT_ID,
	url = "${integration.citizen.url}",
	configuration = CitizenConfiguration.class)
public interface CitizenClient {

	@GetMapping(path = "/{municipalityId}/{personId}", produces = APPLICATION_JSON_VALUE)
	Optional<CitizenExtended> getCitizen(
		@PathVariable(name = "municipalityId") String municipalityId,
		@PathVariable(name = "personId") String personId);
}
