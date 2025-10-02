package se.sundsvall.postportalservice.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import se.sundsvall.dept44.models.api.paging.PagingMetaData;

@Schema(description = "Messages model")
public class Messages {

	@JsonProperty("_meta")
	@Schema(implementation = PagingMetaData.class, accessMode = READ_ONLY)
	private PagingMetaData metaData;

	@ArraySchema(schema = @Schema(implementation = Message.class, accessMode = READ_ONLY))
	private List<Message> messages;

	public static Messages create() {
		return new Messages();
	}

	public PagingMetaData getMetaData() {
		return metaData;
	}

	public Messages withMetaData(PagingMetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	public void setMetaData(PagingMetaData metaData) {
		this.metaData = metaData;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Messages withMessages(List<Message> messages) {
		this.messages = messages;
		return this;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
