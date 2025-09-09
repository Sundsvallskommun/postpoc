package se.sundsvall.postportalservice.integration.employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DepartmentTest {

	@Test
	void constructorTest() {
		var id = "id";
		var name = "name";
		var department = new Department(id, name);

		assertThat(department).isNotNull();
		assertThat(department.identifier()).isEqualTo(id);
		assertThat(department.name()).isEqualTo(name);
	}
}
