package se.sundsvall.postportalservice.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.CoreMatchers.allOf;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

class AttachmentTest {

	private final String name = "attachment.pdf";
	private final String contentType = "application/pdf";
	private final String content = "ZGV0dGEgw6RyIGJhc2U2NA==";
	private final Attachment.DeliveryMode deliveryMode = Attachment.DeliveryMode.SNAIL_MAIL;
	private final String deviation = "A3 Ritning";

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testBean() {
		org.hamcrest.MatcherAssert.assertThat(Attachment.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void builderPattern() {
		final var attachment = Attachment.create()
			.withName(name)
			.withContentType(contentType)
			.withContent(content)
			.withDeliveryMode(deliveryMode)
			.withDeviation(deviation);

		assertThat(attachment.getName()).isEqualTo(name);
		assertThat(attachment.getContentType()).isEqualTo(contentType);
		assertThat(attachment.getContent()).isEqualTo(content);
		assertThat(attachment.getDeliveryMode()).isEqualTo(deliveryMode);
		assertThat(attachment.getDeviation()).isEqualTo(deviation);
		assertThat(attachment).hasNoNullFieldsOrProperties();
	}

	@Test
	void setterAndGetter() {
		final var attachment = new Attachment();
		attachment.setName(name);
		attachment.setContentType(contentType);
		attachment.setContent(content);
		attachment.setDeliveryMode(deliveryMode);
		attachment.setDeviation(deviation);

		assertThat(attachment.getName()).isEqualTo(name);
		assertThat(attachment.getContentType()).isEqualTo(contentType);
		assertThat(attachment.getContent()).isEqualTo(content);
		assertThat(attachment.getDeliveryMode()).isEqualTo(deliveryMode);
		assertThat(attachment.getDeviation()).isEqualTo(deviation);
		assertThat(attachment).hasNoNullFieldsOrProperties();
	}

	@Test
	void validateEmptyBean() {
		final var attachment = new Attachment();

		final var violations = validator.validate(attachment);

		assertThat(violations).hasSize(4)
			.extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
			.containsExactlyInAnyOrder(
				tuple("name", "must not be blank"),
				tuple("contentType", "must be one of: [application/pdf, text/html]"),
				tuple("content", "not a valid BASE64-encoded string"),
				tuple("deliveryMode", "must not be null"));

		assertThat(attachment).hasAllNullFieldsOrProperties();
	}

	@Test
	void validatePopulatedBean() {
		final var attachment = Attachment.create()
			.withName(name)
			.withContentType(contentType)
			.withContent(content)
			.withDeliveryMode(deliveryMode)
			.withDeviation(deviation);

		final var violations = validator.validate(attachment);

		assertThat(violations).isEmpty();
		assertThat(attachment).hasNoNullFieldsOrProperties();
	}

	@Test
	void noDirtOnCreatedBean() {
		assertThat(Attachment.create()).hasAllNullFieldsOrProperties();
		assertThat(new Attachment()).hasAllNullFieldsOrProperties();
	}

}
