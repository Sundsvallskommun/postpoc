package se.sundsvall.postportalservice.api.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;

@Schema(description = "Precheck request model")
public class PrecheckRequest {

	@ArraySchema(schema = @Schema(description = "List of recipients", implementation = String.class))
	@NotEmpty
	private List<@ValidUuid String> recipients;

	public static PrecheckRequest create() {
		return new PrecheckRequest();
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public PrecheckRequest withRecipients(List<String> recipients) {
		this.recipients = recipients;
		return this;
	}

	@Override
	public String toString() {
		return "PrecheckRequest{" +
			"recipients=" + recipients +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		PrecheckRequest that = (PrecheckRequest) o;
		return Objects.equals(recipients, that.recipients);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(recipients);
	}
}
