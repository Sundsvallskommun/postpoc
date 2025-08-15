package se.sundsvall.postportalservice.integration.db;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class UserEntityTest {

	private static final String ID = "123e4567-e89b-12d3-a456-426614174000";
	private static final String NAME = "name";

	@Test
	void testBean() {
		assertThat(UserEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testGettersAndSetters() {
		final var userEntity = new UserEntity();
		userEntity.setId(ID);
		userEntity.setName(NAME);

		assertThat(userEntity.getId()).isEqualTo(ID);
		assertThat(userEntity.getName()).isEqualTo(NAME);
		assertThat(userEntity).hasNoNullFieldsOrProperties();
	}

	@Test
	void constructorTest() {
		final var userEntity = new UserEntity();
		assertThat(userEntity).hasAllNullFieldsOrProperties();
	}
}
