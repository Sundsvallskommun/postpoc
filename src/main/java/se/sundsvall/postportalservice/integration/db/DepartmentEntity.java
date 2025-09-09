package se.sundsvall.postportalservice.integration.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "department")
public class DepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "name", columnDefinition = "VARCHAR(100)")
	private String name;

	@Column(name = "organization_id", columnDefinition = "VARCHAR(12)")
	private String organizationId;

	public static DepartmentEntity create() {
		return new DepartmentEntity();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DepartmentEntity withId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartmentEntity withName(String name) {
		this.name = name;
		return this;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String orgId) {
		this.organizationId = orgId;
	}

	public DepartmentEntity withOrganizationId(String organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	@Override
	public String toString() {
		return "DepartmentEntity{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", organizationId='" + organizationId + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		DepartmentEntity that = (DepartmentEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(organizationId, that.organizationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, organizationId);
	}
}
