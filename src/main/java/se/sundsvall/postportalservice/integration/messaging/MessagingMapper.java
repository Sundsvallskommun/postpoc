package se.sundsvall.postportalservice.integration.messaging;

import generated.se.sundsvall.messaging.Address;
import generated.se.sundsvall.messaging.DigitalMailAttachment;
import generated.se.sundsvall.messaging.DigitalMailParty;
import generated.se.sundsvall.messaging.DigitalMailRequest;
import generated.se.sundsvall.messaging.DigitalMailSender;
import generated.se.sundsvall.messaging.DigitalMailSenderSupportInfo;
import generated.se.sundsvall.messaging.SmsBatchRequest;
import generated.se.sundsvall.messaging.SmsRequest;
import generated.se.sundsvall.messaging.SmsRequestParty;
import generated.se.sundsvall.messaging.SnailmailAttachment;
import generated.se.sundsvall.messaging.SnailmailRequest;
import java.util.List;
import java.util.UUID;
import se.sundsvall.postportalservice.integration.db.AttachmentEntity;
import se.sundsvall.postportalservice.integration.db.MessageEntity;
import se.sundsvall.postportalservice.integration.db.RecipientEntity;
import se.sundsvall.postportalservice.service.util.BlobUtil;

public final class MessagingMapper {

	private MessagingMapper() {}

	public static SmsRequest toSmsRequest(final MessageEntity messageEntity, final RecipientEntity recipientEntity) {
		return new SmsRequest()
			.message(messageEntity.getBody())
			.department(messageEntity.getDepartment().getName())
			.mobileNumber(recipientEntity.getPhoneNumber())
			.party(new SmsRequestParty().partyId(recipientEntity.getPartyId()));
	}

	public static SmsBatchRequest toSmsBatchRequest() {
		return new SmsBatchRequest();
	}

	public static DigitalMailRequest toDigitalMailRequest(final MessageEntity messageEntity, final RecipientEntity recipientEntity) {
		return new DigitalMailRequest()
			.contentType(DigitalMailRequest.ContentTypeEnum.fromValue(messageEntity.getContentType()))
			.body(messageEntity.getBody())
			.subject(messageEntity.getSubject())
			.department(messageEntity.getDepartment().getName())
			.party(new DigitalMailParty().partyIds(List.of(UUID.fromString(recipientEntity.getPartyId()))))
			.attachments(toDigitalMailAttachments(messageEntity.getAttachments()))
			.sender(new DigitalMailSender().supportInfo(new DigitalMailSenderSupportInfo()
				.emailAddress(messageEntity.getDepartment().getContactInformationEmail())
				.phoneNumber(messageEntity.getDepartment().getContactInformationPhoneNumber())
				.url(messageEntity.getDepartment().getContactInformationUrl())
				.text(messageEntity.getDepartment().getSupportText())));

	}

	public static List<DigitalMailAttachment> toDigitalMailAttachments(final List<AttachmentEntity> attachmentEntities) {
		return attachmentEntities.stream()
			.map(MessagingMapper::toDigitalMailAttachment)
			.toList();
	}

	public static DigitalMailAttachment toDigitalMailAttachment(final AttachmentEntity attachmentEntity) {
		return new DigitalMailAttachment()
			.filename(attachmentEntity.getFileName())
			.content(BlobUtil.convertBlobToBase64String(attachmentEntity.getContent()))
			.contentType(DigitalMailAttachment.ContentTypeEnum.fromValue(attachmentEntity.getContentType()));
	}

	public static SnailmailRequest toSnailmailRequest(final MessageEntity messageEntity, final RecipientEntity recipientEntity) {
		return new SnailmailRequest()
			.address(toAddress(recipientEntity))
			.attachments(toSnailmailAttachments(messageEntity.getAttachments()))
			.department(messageEntity.getDepartment().getName());
	}

	public static List<SnailmailAttachment> toSnailmailAttachments(final List<AttachmentEntity> attachmentEntities) {
		return attachmentEntities.stream()
			.map(MessagingMapper::toSnailmailAttachment)
			.toList();
	}

	public static SnailmailAttachment toSnailmailAttachment(final AttachmentEntity attachmentEntity) {
		return new SnailmailAttachment()
			.filename(attachmentEntity.getFileName())
			.content(BlobUtil.convertBlobToBase64String(attachmentEntity.getContent()))
			.contentType(attachmentEntity.getContentType());
	}

	public static Address toAddress(final RecipientEntity recipientEntity) {
		return new Address()
			.address(recipientEntity.getStreetAddress())
			.apartmentNumber(recipientEntity.getApartmentNumber())
			.careOf(recipientEntity.getCareOf())
			.city(recipientEntity.getCity())
			.country(recipientEntity.getCountry())
			.firstName(recipientEntity.getFirstName())
			.lastName(recipientEntity.getLastName())
			.zipCode(recipientEntity.getZipCode());
	}

}
