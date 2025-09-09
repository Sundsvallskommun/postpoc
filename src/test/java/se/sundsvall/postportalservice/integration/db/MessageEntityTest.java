package se.sundsvall.postportalservice.integration.db;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.postportalservice.integration.db.converter.MessageType;

class MessageEntityTest {

	private static final String ID = "id";
	private static final String MESSAGING_ID = "messagingId";
	private static final MessageType ORIGINAL_MESSAGE_TYPE = MessageType.SMS;
	private static final String TEXT = "text";
	private static final OffsetDateTime CREATED = now();

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(MessageEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("attachments"),
			hasValidBeanEqualsExcluding("attachments"),
			hasValidBeanToStringExcluding("attachments")));
	}

	@Test
	void getterAndSetterTest() {
		var messageEntity = new MessageEntity();
		var userEntity = new UserEntity();
		var departmentEntity = new DepartmentEntity();
		var attachments = new ArrayList<AttachmentEntity>();
		var recipientEntities = new ArrayList<RecipientEntity>();

		messageEntity.setId(ID);
		messageEntity.setMessagingId(MESSAGING_ID);
		messageEntity.setOriginalMessageType(ORIGINAL_MESSAGE_TYPE);
		messageEntity.setText(TEXT);
		messageEntity.setCreated(CREATED);
		messageEntity.setUser(userEntity);
		messageEntity.setDepartment(departmentEntity);
		messageEntity.setAttachments(attachments);
		messageEntity.setRecipients(recipientEntities);

		assertThat(messageEntity.getId()).isEqualTo(ID);
		assertThat(messageEntity.getMessagingId()).isEqualTo(MESSAGING_ID);
		assertThat(messageEntity.getOriginalMessageType()).isEqualTo(ORIGINAL_MESSAGE_TYPE);
		assertThat(messageEntity.getText()).isEqualTo(TEXT);
		assertThat(messageEntity.getCreated()).isEqualTo(CREATED);
		assertThat(messageEntity.getUser()).isEqualTo(userEntity);
		assertThat(messageEntity.getDepartment()).isEqualTo(departmentEntity);
		assertThat(messageEntity.getAttachments()).isEqualTo(attachments);
		assertThat(messageEntity.getRecipients()).isEqualTo(recipientEntities);
		assertThat(messageEntity).hasNoNullFieldsOrProperties();
	}

	@Test
	void constructorTest() {
		var messageEntity = new MessageEntity();
		assertThat(messageEntity).hasAllNullFieldsOrPropertiesExcept("attachments", "recipients");
	}
}
