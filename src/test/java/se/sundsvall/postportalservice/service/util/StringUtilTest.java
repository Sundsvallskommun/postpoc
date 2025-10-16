package se.sundsvall.postportalservice.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringUtilTest {

	@ParameterizedTest(name = "{0}")
	@MethodSource("calculateFullNameProvider")
	void calculateFullName(String testName, String firstName, String lastName, String expectedResult) {
		assertThat(StringUtil.calculateFullName(firstName, lastName)).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> calculateFullNameProvider() {
		return Stream.of(
			Arguments.of("No values for either first or last name", null, null, null),
			Arguments.of("Only value for first name", "First", null, "First"),
			Arguments.of("Only value for last name", null, "Last", "Last"),
			Arguments.of("Values for both first and last name", "First", "Last", "First Last"),
			Arguments.of("Test of trimming values for both first and last name", " First ", " Last ", "First Last"));

	}
}
