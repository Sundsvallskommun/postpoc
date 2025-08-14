package se.sundsvall.postportalservice.integration.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import se.sundsvall.postportalservice.integration.db.converter.MessageStatus;
import se.sundsvall.postportalservice.integration.db.converter.MessageType;

@Entity
@Table(name = "message")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "message_id", columnDefinition = "VARCHAR(36)")
	private String messageId;

	@Column(name = "batch_id", columnDefinition = "VARCHAR(36)")
	private String batchId;

	@Column(name = "type", columnDefinition = "VARCHAR(50)")
	private MessageType messageType;

	@Column(name = "status", columnDefinition = "VARCHAR(50)")
	private MessageStatus messageStatus;

	@Column(name = "text", columnDefinition = "TEXT")
	private String text;

	@ManyToOne
	@JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36)")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "department_id", columnDefinition = "VARCHAR(36)")
	private DepartmentEntity department;

	@ManyToMany
	@JoinTable(name = "message_attachment",
		joinColumns = @JoinColumn(name = "message_id"),
		inverseJoinColumns = @JoinColumn(name = "attachment_id"))
	private List<AttachmentEntity> attachments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public List<AttachmentEntity> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentEntity> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "MessageEntity{" +
			"id='" + id + '\'' +
			", messageId='" + messageId + '\'' +
			", batchId='" + batchId + '\'' +
			", messageType=" + messageType +
			", messageStatus=" + messageStatus +
			", text='" + text + '\'' +
			", user=" + user +
			", department=" + department +
			", attachments=" + attachments +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		MessageEntity that = (MessageEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(messageId, that.messageId) && Objects.equals(batchId, that.batchId) && messageType == that.messageType && messageStatus == that.messageStatus
			&& Objects.equals(text, that.text) && Objects.equals(user, that.user) && Objects.equals(department, that.department) && Objects.equals(attachments, that.attachments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, messageId, batchId, messageType, messageStatus, text, user, department, attachments);
	}
}
