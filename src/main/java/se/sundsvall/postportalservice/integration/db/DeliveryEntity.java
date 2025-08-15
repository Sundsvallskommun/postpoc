package se.sundsvall.postportalservice.integration.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Objects;
import se.sundsvall.postportalservice.integration.db.converter.MessageStatus;
import se.sundsvall.postportalservice.integration.db.converter.MessageType;

@Entity
@Table(name = "delivery", indexes = {
	@Index(name = "IDX_DELIVERY_RECIPIENT_ID", columnList = "recipient_id"),
	@Index(name = "IDX_DELIVERY_TYPE_STATUS", columnList = "type, status")
})
public class DeliveryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "status", columnDefinition = "VARCHAR(50)")
	private MessageStatus messageStatus;

	@Column(name = "type", columnDefinition = "VARCHAR(50)")
	private MessageType messageType;

	@Column(name = "status_detail", columnDefinition = "TEXT")
	private String statusDetail;

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

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "DeliveryEntity{" +
			"id='" + id + '\'' +
			", messageStatus=" + messageStatus +
			", messageType=" + messageType +
			", statusDetail='" + statusDetail + '\'' +
			", created=" + created +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		DeliveryEntity that = (DeliveryEntity) o;
		return Objects.equals(id, that.id) && messageStatus == that.messageStatus && messageType == that.messageType && Objects.equals(statusDetail, that.statusDetail) && Objects.equals(created, that.created);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, messageStatus, messageType, statusDetail, created);
	}
}
