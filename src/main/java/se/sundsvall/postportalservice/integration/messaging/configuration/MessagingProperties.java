package se.sundsvall.postportalservice.integration.messaging.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("integration.messaging")
public record MessagingProperties(
	@DefaultValue("5") int connectTimeout,
	@DefaultValue("30") int readTimeout) {
}
