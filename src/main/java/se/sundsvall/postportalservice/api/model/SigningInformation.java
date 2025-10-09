package se.sundsvall.postportalservice.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Objects;

@Schema(description = "SigningInformation model")
public class SigningInformation {

	@Schema(description = "Status of the signing order")
	private String status;

	@Schema(description = "Timestamp when the letter was signed by receiving party")
	private OffsetDateTime signedAt;

	@Schema(description = "The unique Kivra id for the signing order")
	private String contentKey;

	@Schema(description = "Order reference in Kivra for the signing order")
	private String orderReference;

	@Schema(description = "The signature made by the receiving party")
	private String signature;

	@Schema(description = "Online certificate status protocol for the signing order")
	private String ocspResponse;

	@Schema(description = "Information about the user that signed the letter")
	private User user;

	@Schema(description = "Information about the device used when signing the letter")
	private Device device;

	@Schema(description = "Step-up information for the signing order")
	private StepUp stepUp;

	public static SigningInformation create() {
		return new SigningInformation();
	}

	public String getStatus() {
		return status;
	}

	public SigningInformation withStatus(String status) {
		this.status = status;
		return this;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffsetDateTime getSignedAt() {
		return signedAt;
	}

	public SigningInformation withSignedAt(OffsetDateTime signedAt) {
		this.signedAt = signedAt;
		return this;
	}

	public void setSignedAt(OffsetDateTime signedAt) {
		this.signedAt = signedAt;
	}

	public String getContentKey() {
		return contentKey;
	}

	public SigningInformation withContentKey(String contentKey) {
		this.contentKey = contentKey;
		return this;
	}

	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public SigningInformation withOrderReference(String orderRef) {
		this.orderReference = orderRef;
		return this;
	}

	public void setOrderReference(String orderRef) {
		this.orderReference = orderRef;
	}

	public String getSignature() {
		return signature;
	}

	public SigningInformation withSignature(String signature) {
		this.signature = signature;
		return this;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOcspResponse() {
		return ocspResponse;
	}

	public SigningInformation withOcspResponse(String ocspResponse) {
		this.ocspResponse = ocspResponse;
		return this;
	}

	public void setOcspResponse(String ocspResponse) {
		this.ocspResponse = ocspResponse;
	}

	public User getUser() {
		return user;
	}

	public SigningInformation withUser(User user) {
		this.user = user;
		return this;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Device getDevice() {
		return device;
	}

	public SigningInformation withDevice(Device device) {
		this.device = device;
		return this;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public StepUp getStepUp() {
		return stepUp;
	}

	public SigningInformation withStepUp(StepUp stepUp) {
		this.stepUp = stepUp;
		return this;
	}

	public void setStepUp(StepUp stepUp) {
		this.stepUp = stepUp;
	}

	@Override
	public String toString() {
		return "SigningInformation{" +
			"status='" + status + '\'' +
			", signedAt=" + signedAt +
			", contentKey='" + contentKey + '\'' +
			", orderReference='" + orderReference + '\'' +
			", signature='" + signature + '\'' +
			", ocspResponse='" + ocspResponse + '\'' +
			", user=" + user +
			", device=" + device +
			", stepUp=" + stepUp +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		SigningInformation that = (SigningInformation) o;
		return Objects.equals(status, that.status) && Objects.equals(signedAt, that.signedAt) && Objects.equals(contentKey, that.contentKey) && Objects.equals(orderReference, that.orderReference) && Objects.equals(
			signature, that.signature) && Objects.equals(ocspResponse, that.ocspResponse) && Objects.equals(user, that.user) && Objects.equals(device, that.device) && Objects.equals(stepUp, that.stepUp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, signedAt, contentKey, orderReference, signature, ocspResponse, user, device, stepUp);
	}

	public static class User {

		@Schema(description = "Personal identity number for the signing party")
		private String personalIdentityNumber;

		@Schema(description = "Full name of the signing party")
		private String name;

		@Schema(description = "First name of the signing party")
		private String givenName;

		@Schema(description = "Last name of the signing party")
		private String surname;

		public static User create() {
			return new User();
		}

		public String getPersonalIdentityNumber() {
			return personalIdentityNumber;
		}

		public User withPersonalIdentityNumber(String personalIdentityNumber) {
			this.personalIdentityNumber = personalIdentityNumber;
			return this;
		}

		public void setPersonalIdentityNumber(String personalIdentityNumber) {
			this.personalIdentityNumber = personalIdentityNumber;
		}

		public String getName() {
			return name;
		}

		public User withName(String name) {
			this.name = name;
			return this;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGivenName() {
			return givenName;
		}

		public User withGivenName(String givenName) {
			this.givenName = givenName;
			return this;
		}

		public void setGivenName(String givenName) {
			this.givenName = givenName;
		}

		public String getSurname() {
			return surname;
		}

		public User withSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		@Override
		public String toString() {
			return "User{" +
				"personalIdentityNumber='" + personalIdentityNumber + '\'' +
				", name='" + name + '\'' +
				", givenName='" + givenName + '\'' +
				", surname='" + surname + '\'' +
				'}';
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass())
				return false;
			User user = (User) o;
			return Objects.equals(personalIdentityNumber, user.personalIdentityNumber) && Objects.equals(name, user.name) && Objects.equals(givenName, user.givenName) && Objects.equals(surname, user.surname);
		}

		@Override
		public int hashCode() {
			return Objects.hash(personalIdentityNumber, name, givenName, surname);
		}
	}

	public static class Device {

		@Schema(description = "Ip address used when the letter was signed")
		private String ipAddress;

		public static Device create() {
			return new Device();
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public Device withIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
			return this;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		@Override
		public String toString() {
			return "Device{" +
				"ipAddress='" + ipAddress + '\'' +
				'}';
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass())
				return false;
			Device device = (Device) o;
			return Objects.equals(ipAddress, device.ipAddress);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(ipAddress);
		}
	}

	public static class StepUp {

		@Schema(description = "Whether an MRTD check was performed before the order was completed")
		private Boolean mrtd;

		public static StepUp create() {
			return new StepUp();
		}

		public Boolean getMrtd() {
			return mrtd;
		}

		public StepUp withMrtd(Boolean mrtd) {
			this.mrtd = mrtd;
			return this;
		}

		public void setMrtd(Boolean mrtd) {
			this.mrtd = mrtd;
		}

		@Override
		public String toString() {
			return "StepUp{" +
				"mrtd=" + mrtd +
				'}';
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass())
				return false;
			StepUp stepUp = (StepUp) o;
			return Objects.equals(mrtd, stepUp.mrtd);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(mrtd);
		}
	}

}
