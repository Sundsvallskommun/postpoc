package se.sundsvall.postportalservice.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Objects;

@Schema(description = "Message model")
public class Message {

	@Schema(description = "Message ID", accessMode = READ_ONLY, example = "123456")
	private String messageId;

	@Schema(description = "The subject", accessMode = READ_ONLY, example = "Viktig information")
	private String subject;

	@Schema(description = "Type of message", accessMode = READ_ONLY, example = "SMS|LETTER|DIGITAL_REGISTERED_LETTER")
	private String type;

	@Schema(description = "When the message was sent", accessMode = READ_ONLY, example = "2021-01-01T12:00:00")
	private LocalDateTime sentAt;

	@Schema(description = "Status for signing process. Only applicable for message type DIGITAL_REGISTERED_LETTER", requiredMode = NOT_REQUIRED, accessMode = READ_ONLY)
	private SigningStatus signingStatus;

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

	public SigningStatus getSigningStatus() {
		return signingStatus;
	}

	public Message withSigningStatus(final SigningStatus signingStatus) {
		this.signingStatus = signingStatus;
		return this;
	}

	public void setSigningStatus(final SigningStatus signingStatus) {
		this.signingStatus = signingStatus;
	}

	@Override
	public String toString() {
		return "Message{" +
			"messageId='" + messageId + '\'' +
			", subject='" + subject + '\'' +
			", type='" + type + '\'' +
			", sentAt=" + sentAt +
			", signingStatus=" + signingStatus +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final var message = (Message) o;
		return Objects.equals(messageId, message.messageId) && Objects.equals(subject, message.subject) &&
			Objects.equals(type, message.type) && Objects.equals(sentAt, message.sentAt) &&
			Objects.equals(signingStatus, message.signingStatus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(messageId, subject, type, sentAt, signingStatus);
	}
}
