package se.sundsvall.postportalservice.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;

import java.time.LocalDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MessageTest {

	private static final String MESSAGE_ID = "messageId";
	private static final String SUBJECT = "subject";
	private static final String TYPE = "type";
	private static final LocalDateTime SENT_AT = LocalDateTime.of(2021, 1, 1, 12, 0, 0);

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDateTime.class);
	}

	@Test
	void testBean() {
		org.hamcrest.MatcherAssert.assertThat(Message.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void getterAndSetterTest() {
		var bean = new Message();
		bean.setMessageId(MESSAGE_ID);
		bean.setSubject(SUBJECT);
		bean.setType(TYPE);
		bean.setSentAt(SENT_AT);

		assertThat(bean.getMessageId()).isEqualTo(MESSAGE_ID);
		assertThat(bean.getSubject()).isEqualTo(SUBJECT);
		assertThat(bean.getType()).isEqualTo(TYPE);
		assertThat(bean.getSentAt()).isEqualTo(SENT_AT);
	}

	@Test
	void builderPatternTest() {
		final var bean = new Message()
			.withMessageId(MESSAGE_ID)
			.withSubject(SUBJECT)
			.withType(TYPE)
			.withSentAt(SENT_AT);

		assertThat(bean.getMessageId()).isEqualTo(MESSAGE_ID);
		assertThat(bean.getSubject()).isEqualTo(SUBJECT);
		assertThat(bean.getType()).isEqualTo(TYPE);
		assertThat(bean.getSentAt()).isEqualTo(SENT_AT);
	}

	@Test
	void constructorTest() {
		assertThat(new Message()).hasAllNullFieldsOrProperties();
		assertThat(new Message()).hasOnlyFields("messageId", "subject", "type", "sentAt");
	}

}
