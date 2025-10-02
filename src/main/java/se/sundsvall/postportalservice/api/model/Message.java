package se.sundsvall.postportalservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Message model")
public class Message {

	@Schema(description = "Message ID", accessMode = Schema.AccessMode.READ_ONLY, example = "123456")
	private String messageId;

	@Schema(description = "The subject", accessMode = Schema.AccessMode.READ_ONLY, example = "Viktig information")
	private String subject;

	@Schema(description = "Type of message", accessMode = Schema.AccessMode.READ_ONLY, example = "SMS|LETTER|DIGITAL_REGISTERED_LETTER")
	private String type;

	@Schema(description = "When the message was sent", accessMode = Schema.AccessMode.READ_ONLY, example = "2021-01-01T12:00:00")
	private LocalDateTime sentAt;

	public static Message create() {
		return new Message();
	}

	public String getMessageId() {
		return messageId;
	}

	public Message withMessageId(final String messageId) {
		this.messageId = messageId;
		return this;
	}

	public void setMessageId(final String messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public Message withSubject(final String subject) {
		this.subject = subject;
		return this;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public Message withType(final String type) {
		this.type = type;
		return this;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public Message withSentAt(final LocalDateTime sentAt) {
		this.sentAt = sentAt;
		return this;
	}

	public void setSentAt(final LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
}
