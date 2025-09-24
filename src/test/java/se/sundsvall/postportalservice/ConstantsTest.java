package se.sundsvall.postportalservice;

import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.postportalservice.Constants.ORIGIN;

import org.junit.jupiter.api.Test;

class ConstantsTest {

	@Test
	void testOriginValue() {
		assertThat(ORIGIN).isEqualTo("PostPortalService");
	}
}
