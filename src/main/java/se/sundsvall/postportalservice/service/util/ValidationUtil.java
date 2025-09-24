package se.sundsvall.postportalservice.service.util;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public final class ValidationUtil {

	private ValidationUtil() {}

	public static <T> void validate(final T object) {
		try (var validatorFactory = Validation.buildDefaultValidatorFactory()) {
			var validator = validatorFactory.getValidator();
			var violations = validator.validate(object);
			if (!violations.isEmpty()) {
				throw new ConstraintViolationException(violations);
			}
		}
	}
}
