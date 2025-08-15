package se.sundsvall.postportalservice.integration.db;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.OffsetDateTime;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.postportalservice.integration.db.converter.MessageStatus;
import se.sundsvall.postportalservice.integration.db.converter.MessageType;

class DeliveryEntityTest {

	private static final String ID = "123e4567-e89b-12d3-a456-426614174000";
	private static final MessageStatus STATUS = MessageStatus.SENT;
	private static final MessageType TYPE = MessageType.SMS;
	private static final String DETAIL = "Package delivered successfully";
	private static final OffsetDateTime CREATED = now();

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(DeliveryEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void getterAndSetterTest() {
		var deliveryEntity = new DeliveryEntity();
		deliveryEntity.setId(ID);
		deliveryEntity.setMessageStatus(STATUS);
		deliveryEntity.setMessageType(TYPE);
		deliveryEntity.setStatusDetail(DETAIL);
		deliveryEntity.setCreated(CREATED);

		assertThat(deliveryEntity.getId()).isEqualTo(ID);
		assertThat(deliveryEntity.getMessageStatus()).isEqualTo(STATUS);
		assertThat(deliveryEntity.getMessageType()).isEqualTo(TYPE);
		assertThat(deliveryEntity.getStatusDetail()).isEqualTo(DETAIL);
		assertThat(deliveryEntity.getCreated()).isEqualTo(CREATED);
		assertThat(deliveryEntity).hasNoNullFieldsOrProperties();
	}

	@Test
	void constructorTest() {
		var deliveryEntity = new DeliveryEntity();
		Assertions.assertThat(deliveryEntity).hasAllNullFieldsOrPropertiesExcept();
	}
}
