package se.sundsvall.postportalservice.service.util;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.allNull;

public final class StringUtil {
	private StringUtil() {}

	public static String calculateFullName(final String firstName, final String lastName) {
		if (allNull(firstName, lastName)) {
			return null;
		}

		return "%s %s".formatted(
			ofNullable(firstName).map(String::trim).orElse(""),
			ofNullable(lastName).map(String::trim).orElse("")).trim();
	}
}
