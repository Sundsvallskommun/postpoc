package se.sundsvall.postportalservice.integration.employee.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("integration.employee")
public record EmployeeProperties(
	@DefaultValue("5") int connectTimeout,
	@DefaultValue("30") int readTimeout) {
}
