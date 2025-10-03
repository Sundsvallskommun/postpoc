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
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MessageDetailsTest {

	// MessageDetails attributes
	private static final String SUBJECT = "subject";
	private static final LocalDateTime SENT_AT = now();
	private static final List<MessageDetails.Attachment> ATTACHMENTS = List.of(new MessageDetails.Attachment());
	private static final List<MessageDetails.Recipient> RECIPIENTS = List.of(new MessageDetails.Recipient());

	// MessageDetails.Attachment attributes
	private static final String ATTACHMENT_ID = "attachmentId";
	private static final String FILE_NAME = "fileName";
	private static final String CONTENT_TYPE = "contentType";

	// MessageDetails.Recipient attributes
	private static final String NAME = "name";
	private static final String PARTY_ID = "partyId";
	private static final String MOBILE_NUMBER = "mobileNumber";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String ZIP_CODE = "zipCode";
	private static final String CITY = "city";
	private static final String MESSAGE_TYPE = "messageType";

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDateTime.class);
	}

	@Test
	void testMessageDetails() {
		org.hamcrest.MatcherAssert.assertThat(MessageDetails.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testMessageDetailsAttachment() {
		org.hamcrest.MatcherAssert.assertThat(MessageDetails.Attachment.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testMessageDetailsRecipient() {
		org.hamcrest.MatcherAssert.assertThat(MessageDetails.Recipient.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void MessageDetails_getterAndSetterTest() {
		final var bean = new MessageDetails();
		bean.setSubject(SUBJECT);
		bean.setSentAt(SENT_AT);
		bean.setAttachments(ATTACHMENTS);
		bean.setRecipients(RECIPIENTS);

		assertThat(bean.getSubject()).isEqualTo(SUBJECT);
		assertThat(bean.getSentAt()).isEqualTo(SENT_AT);
		assertThat(bean.getAttachments()).isEqualTo(ATTACHMENTS);
		assertThat(bean.getRecipients()).isEqualTo(RECIPIENTS);
	}

	@Test
	void MessageDetails_Attachment_getterAndSetterTest() {
		final var bean = new MessageDetails.Attachment();
		bean.setAttachmentId(ATTACHMENT_ID);
		bean.setFileName(FILE_NAME);
		bean.setContentType(CONTENT_TYPE);

		assertThat(bean.getAttachmentId()).isEqualTo(ATTACHMENT_ID);
		assertThat(bean.getFileName()).isEqualTo(FILE_NAME);
		assertThat(bean.getContentType()).isEqualTo(CONTENT_TYPE);
	}

	@Test
	void MessageDetails_Recipient_getterAndSetterTest() {
		final var bean = new MessageDetails.Recipient();
		bean.setName(NAME);
		bean.setPartyId(PARTY_ID);
		bean.setMobileNumber(MOBILE_NUMBER);
		bean.setStreetAddress(STREET_ADDRESS);
		bean.setZipCode(ZIP_CODE);
		bean.setCity(CITY);
		bean.setMessageType(MESSAGE_TYPE);

		assertThat(bean.getName()).isEqualTo(NAME);
		assertThat(bean.getPartyId()).isEqualTo(PARTY_ID);
		assertThat(bean.getMobileNumber()).isEqualTo(MOBILE_NUMBER);
		assertThat(bean.getStreetAddress()).isEqualTo(STREET_ADDRESS);
		assertThat(bean.getZipCode()).isEqualTo(ZIP_CODE);
		assertThat(bean.getCity()).isEqualTo(CITY);
		assertThat(bean.getMessageType()).isEqualTo(MESSAGE_TYPE);
	}

	@Test
	void MessageDetails_builderPatternTest() {
		final var bean = MessageDetails.create()
			.withSubject(SUBJECT)
			.withSentAt(SENT_AT)
			.withAttachments(ATTACHMENTS)
			.withRecipients(RECIPIENTS);

		assertThat(bean.getSubject()).isEqualTo(SUBJECT);
		assertThat(bean.getSentAt()).isEqualTo(SENT_AT);
		assertThat(bean.getAttachments()).isEqualTo(ATTACHMENTS);
		assertThat(bean.getRecipients()).isEqualTo(RECIPIENTS);
	}

	@Test
	void MessageDetails_Attachment_builderPatternTest() {
		final var bean = MessageDetails.Attachment.create()
			.withAttachmentId(ATTACHMENT_ID)
			.withFileName(FILE_NAME)
			.withContentType(CONTENT_TYPE);

		assertThat(bean.getAttachmentId()).isEqualTo(ATTACHMENT_ID);
		assertThat(bean.getFileName()).isEqualTo(FILE_NAME);
		assertThat(bean.getContentType()).isEqualTo(CONTENT_TYPE);
	}

	@Test
	void MessageDetails_Recipient_builderPatternTest() {
		final var bean = MessageDetails.Recipient.create()
			.withName(NAME)
			.withPartyId(PARTY_ID)
			.withMobileNumber(MOBILE_NUMBER)
			.withStreetAddress(STREET_ADDRESS)
			.withZipCode(ZIP_CODE)
			.withCity(CITY)
			.withMessageType(MESSAGE_TYPE);

		assertThat(bean.getName()).isEqualTo(NAME);
		assertThat(bean.getPartyId()).isEqualTo(PARTY_ID);
		assertThat(bean.getMobileNumber()).isEqualTo(MOBILE_NUMBER);
		assertThat(bean.getStreetAddress()).isEqualTo(STREET_ADDRESS);
		assertThat(bean.getZipCode()).isEqualTo(ZIP_CODE);
		assertThat(bean.getCity()).isEqualTo(CITY);
		assertThat(bean.getMessageType()).isEqualTo(MESSAGE_TYPE);
	}

	@Test
	void MessageDetails_constructorTest() {
		assertThat(new MessageDetails()).hasAllNullFieldsOrProperties();
		assertThat(new MessageDetails()).hasOnlyFields("subject", "sentAt", "attachments", "recipients");
	}

	@Test
	void MessageDetails_Attachment_constructorTest() {
		assertThat(new MessageDetails.Attachment()).hasAllNullFieldsOrProperties();
		assertThat(new MessageDetails.Attachment()).hasOnlyFields("attachmentId", "fileName", "contentType");
	}

	@Test
	void MessageDetails_Recipient_constructorTest() {
		assertThat(new MessageDetails.Recipient()).hasAllNullFieldsOrProperties();
		assertThat(new MessageDetails.Recipient()).hasOnlyFields("name", "partyId", "mobileNumber", "streetAddress", "zipCode", "city", "messageType");
	}

}
