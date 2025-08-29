package se.sundsvall.postportalservice.integration.digitalregisteredletter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("integration.digitalregisteredletter")
public record DigitalRegisteredLetterProperties(
	@DefaultValue("5") int connectTimeout,
	@DefaultValue("30") int readTimeout) {
}
