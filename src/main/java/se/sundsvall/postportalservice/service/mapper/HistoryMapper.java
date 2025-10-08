package se.sundsvall.postportalservice.service.mapper;

import static java.util.Collections.emptyList;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;
import se.sundsvall.postportalservice.api.model.Message;
import se.sundsvall.postportalservice.api.model.MessageDetails;
import se.sundsvall.postportalservice.integration.db.AttachmentEntity;
import se.sundsvall.postportalservice.integration.db.MessageEntity;
import se.sundsvall.postportalservice.integration.db.RecipientEntity;

@Component
public class HistoryMapper {

	public List<Message> toMessageList(final List<MessageEntity> messageEntities) {
		return Optional.ofNullable(messageEntities).orElse(emptyList()).stream()
			.map(this::toMessage)
			.filter(Objects::nonNull)
			.toList();
	}

	public Message toMessage(final MessageEntity messageEntity) {
		return Optional.ofNullable(messageEntity).map(present -> Message.create()
			.withMessageId(messageEntity.getId())
			.withType(Optional.ofNullable(messageEntity.getMessageType()).map(Objects::toString).orElse(null))
			.withSubject(messageEntity.getSubject())
			.withSentAt(Optional.ofNullable(messageEntity.getCreated()).map(OffsetDateTime::toLocalDateTime).orElse(null)))
			.orElse(null);
	}

	public MessageDetails toMessageDetails(final MessageEntity messageEntity) {
		return Optional.ofNullable(messageEntity).map(present -> MessageDetails.create()
			.withSubject(messageEntity.getSubject())
			.withSentAt(messageEntity.getCreated().toLocalDateTime())
			.withAttachments(toAttachmentList(messageEntity.getAttachments()))
			.withRecipients(toRecipientList(messageEntity.getRecipients())))
			.orElse(null);
	}

	public List<MessageDetails.AttachmentDetails> toAttachmentList(final List<AttachmentEntity> attachments) {
		return Optional.ofNullable(attachments).orElse(emptyList()).stream()
			.map(this::toAttachment)
			.filter(Objects::nonNull)
			.toList();
	}

	public MessageDetails.AttachmentDetails toAttachment(final AttachmentEntity attachmentEntity) {
		return Optional.ofNullable(attachmentEntity).map(present -> MessageDetails.AttachmentDetails.create()
			.withFileName(attachmentEntity.getFileName())
			.withContentType(attachmentEntity.getContentType())
			.withAttachmentId(attachmentEntity.getId()))
			.orElse(null);
	}

	public List<MessageDetails.RecipientDetails> toRecipientList(final List<RecipientEntity> recipientEntities) {
		return Optional.ofNullable(recipientEntities).orElse(emptyList()).stream()
			.map(this::toRecipient)
			.filter(Objects::nonNull)
			.toList();
	}

	public MessageDetails.RecipientDetails toRecipient(final RecipientEntity recipientEntity) {
		return Optional.ofNullable(recipientEntity).map(present -> MessageDetails.RecipientDetails.create()
			.withPartyId(recipientEntity.getPartyId())
			.withCity(recipientEntity.getCity())
			.withStreetAddress(recipientEntity.getStreetAddress())
			.withMobileNumber(recipientEntity.getPhoneNumber())
			.withName("%s %s".formatted(recipientEntity.getFirstName(), recipientEntity.getLastName()))
			.withMessageType(recipientEntity.getMessageType().toString())
			.withZipCode(recipientEntity.getZipCode()))
			.orElse(null);
	}

}
