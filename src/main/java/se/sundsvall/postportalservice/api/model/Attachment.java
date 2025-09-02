package se.sundsvall.postportalservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import se.sundsvall.dept44.common.validators.annotation.OneOf;
import se.sundsvall.dept44.common.validators.annotation.ValidBase64;

@Schema(description = "Attachment model")
public class Attachment {

	@Schema(description = "Name of the attachment", example = "attachment.pdf")
	@NotBlank
	private String name;

	@Schema(description = "Content type of the attachment", example = "application/pdf")
	@OneOf(value = {
		"application/pdf", "text/html"
	})
	private String contentType;

	@Schema(description = "Base64 encoded content of the attachment", example = "ZGV0dGEgw6RyIGJhc2U2NA==")
	@ValidBase64
	private String content;

	@Schema(description = "How the attachment can be delivered", example = "SNAIL_MAIL")
	@NotNull
	private DeliveryMode deliveryMode;

	@Schema(description = "Describes any deviation for SNAIL_MAIL deliveries", example = "A3 Ritning")
	private String deviation;

	public enum DeliveryMode {
		DIGITAL_MAIL, SNAIL_MAIL, ANY
	}

	public static Attachment create() {
		return new Attachment();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Attachment withName(String name) {
		this.name = name;
		return this;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Attachment withContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Attachment withContent(String content) {
		this.content = content;
		return this;
	}

	public DeliveryMode getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(DeliveryMode deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Attachment withDeliveryMode(DeliveryMode deliveryMode) {
		this.deliveryMode = deliveryMode;
		return this;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public Attachment withDeviation(String deviation) {
		this.deviation = deviation;
		return this;
	}

	@Override
	public String toString() {
		return "Attachment{" +
			"name='" + name + '\'' +
			", contentType='" + contentType + '\'' +
			", content='" + content + '\'' +
			", deliveryMode=" + deliveryMode +
			", deviation='" + deviation + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Attachment that = (Attachment) o;
		return Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType) && Objects.equals(content, that.content) && deliveryMode == that.deliveryMode && Objects.equals(deviation, that.deviation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, contentType, content, deliveryMode, deviation);
	}
}
