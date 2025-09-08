package se.sundsvall.postportalservice.integration.citizen.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "integration.citizen")
public record CitizenProperties(
	@DefaultValue("5") int connectTimeout,
	@DefaultValue("30") int readTimeout) {
}
