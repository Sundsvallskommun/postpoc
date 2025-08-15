package se.sundsvall.postportalservice.integration.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipient", indexes = {
	@Index(name = "IDX_RECIPIENT_MESSAGE_ID", columnList = "message_id")
})
public class RecipientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "party_id", columnDefinition = "VARCHAR(36)")
	private String partyId;

	@Column(name = "email", columnDefinition = "VARCHAR(150)")
	private String email;

	@Column(name = "phone_number", columnDefinition = "VARCHAR(20)")
	private String phoneNumber;

	@Column(name = "first_name", columnDefinition = "VARCHAR(100)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "VARCHAR(100)")
	private String lastName;

	@Column(name = "address", columnDefinition = "VARCHAR(255)")
	private String streetAddress;

	@Column(name = "apartment_number", columnDefinition = "VARCHAR(20)")
	private String apartmentNumber;

	@Column(name = "care_of", columnDefinition = "VARCHAR(100)")
	private String careOf;

	@Column(name = "zip_code", columnDefinition = "VARCHAR(10)")
	private String zipCode;

	@Column(name = "city", columnDefinition = "VARCHAR(100)")
	private String city;

	@Column(name = "country", columnDefinition = "VARCHAR(100)")
	private String country;

	@OneToMany
	@JoinColumn(name = "recipient_id", referencedColumnName = "id", columnDefinition = "VARCHAR(36) NOT NULL")
	private List<DeliveryEntity> deliveries;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCareOf() {
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<DeliveryEntity> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<DeliveryEntity> deliveries) {
		this.deliveries = deliveries;
	}

	@Override
	public String toString() {
		return "RecipientEntity{" +
			"id='" + id + '\'' +
			", partyId='" + partyId + '\'' +
			", email='" + email + '\'' +
			", phoneNumber='" + phoneNumber + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", streetAddress='" + streetAddress + '\'' +
			", apartmentNumber='" + apartmentNumber + '\'' +
			", careOf='" + careOf + '\'' +
			", zipCode='" + zipCode + '\'' +
			", city='" + city + '\'' +
			", country='" + country + '\'' +
			", deliveries=" + deliveries +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		RecipientEntity that = (RecipientEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(partyId, that.partyId) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(firstName,
			that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(streetAddress, that.streetAddress) && Objects.equals(apartmentNumber, that.apartmentNumber) && Objects.equals(careOf, that.careOf)
			&& Objects.equals(zipCode, that.zipCode) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(deliveries, that.deliveries);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, partyId, email, phoneNumber, firstName, lastName, streetAddress, apartmentNumber, careOf, zipCode, city, country, deliveries);
	}
}
