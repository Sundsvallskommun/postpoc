package se.sundsvall.postportalservice.integration.db.converter;

public enum MessageStatus {
	PENDING,
	AWAITING_FEEDBACK,
	SENT,
	NOT_SENT,
	FAILED,
	NO_CONTACT_SETTINGS_FOUND,
	NO_CONTACT_WANTED;

	public static MessageStatus fromValue(final String value) {
		for (final var status : MessageStatus.values()) {
			if (status.toString().equalsIgnoreCase(value)) {
				return status;
			}
		}
		// If we don't recognize the value, we default to FAILED.
		return MessageStatus.FAILED;
	}
}
