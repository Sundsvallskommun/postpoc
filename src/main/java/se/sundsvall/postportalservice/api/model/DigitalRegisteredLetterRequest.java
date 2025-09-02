package se.sundsvall.postportalservice.api.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;

@Schema(description = "Digital registered letter request model")
public class DigitalRegisteredLetterRequest {

	@Schema(description = "The party id of the recipient", example = "6d0773d6-3e7f-4552-81bc-f0007af95adf")
	@ValidUuid
	private String partyId;

	@Schema(description = "The subject of the letter", example = "This is the subject of the letter")
	@NotBlank
	private String subject;

	@ArraySchema(schema = @Schema(description = "List of attachments", implementation = Attachment.class))
	@NotEmpty
	private List<@Valid Attachment> attachments;

	public static DigitalRegisteredLetterRequest create() {
		return new DigitalRegisteredLetterRequest();
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public DigitalRegisteredLetterRequest withPartyId(String partyId) {
		this.partyId = partyId;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public DigitalRegisteredLetterRequest withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public DigitalRegisteredLetterRequest withAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
		return this;
	}

	@Override
	public String toString() {
		return "DigitalRegisteredLetterRequest{" +
			"partyId='" + partyId + '\'' +
			", subject='" + subject + '\'' +
			", attachments=" + attachments +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		DigitalRegisteredLetterRequest that = (DigitalRegisteredLetterRequest) o;
		return Objects.equals(partyId, that.partyId) && Objects.equals(subject, that.subject) && Objects.equals(attachments, that.attachments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(partyId, subject, attachments);
	}
}
