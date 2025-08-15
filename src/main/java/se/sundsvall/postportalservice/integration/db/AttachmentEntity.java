package se.sundsvall.postportalservice.integration.db;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "attachment", indexes = {
	@Index(name = "IDX_ATTACHMENT_MESSAGE_ID", columnList = "message_id")
})
public class AttachmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "name", columnDefinition = "VARCHAR(150)")
	private String name;

	@Column(name = "mime_type", columnDefinition = "VARCHAR(50)")
	private String mimeType;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "LONGBLOB")
	private String content;

	@Column(name = "created", columnDefinition = "DATETIME")
	private OffsetDateTime created;

	@PrePersist
	void prePersist() {
		created = OffsetDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "AttachmentEntity{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", mimeType='" + mimeType + '\'' +
			", content='" + content + '\'' +
			", created=" + created +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		AttachmentEntity that = (AttachmentEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(mimeType, that.mimeType) && Objects.equals(content, that.content) && Objects.equals(created, that.created);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, mimeType, content, created);
	}
}
