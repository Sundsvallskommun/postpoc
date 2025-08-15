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
import jakarta.persistence.Table;
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

	@Column(name = "name")
	private String name;

	@Column(name = "mime_type")
	private String mimeType;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "LONGBLOB")
	private String content;

	@Override
	public String toString() {
		return "AttachmentEntity{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", mimeType='" + mimeType + '\'' +
			", content='" + content + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		AttachmentEntity that = (AttachmentEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(mimeType, that.mimeType) && Objects.equals(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, mimeType, content);
	}
}

