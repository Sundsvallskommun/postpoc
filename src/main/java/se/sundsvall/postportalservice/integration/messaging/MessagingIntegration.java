package se.sundsvall.postportalservice.integration.messaging;

import org.springframework.stereotype.Component;

@Component
public class MessagingIntegration {

	private final MessagingClient client;

	public MessagingIntegration(final MessagingClient client) {
		this.client = client;
	}

	public boolean sendDigitalMail(final String municipalityId) {
		// Used to send digital mail.
		// TODO: Implement the mapping and the call to messaging. Also, the response from messaging should be used to update the
		// status of the message.
		return true;
	}

	public boolean sendSms(final String municipalityId) {

		// Used to send SMS.
		// TODO: Implement the mapping and the call to messaging. Also, the response from messaging should be used to update the
		// status of the message.
		return true;
	}

	public boolean sendSmsBatch(final String municipalityId) {
		// Used to send SMS in batch.
		// TODO: Implement the mapping and the call to messaging. Also, the response from messaging should be used to update the
		// status of the message.
		return true;
	}

	public boolean sendSnailMail(final String municipalityId) {
		// Used to send snail mail.
		// TODO: When messaging exposes the new snail mail endpoint, implement the mapping and the call to messaging. Also, the
		// response from messaging should be used to update the status of the message.
		return true;
	}

	public boolean precheckMailBoxes(final String municipalityId) {
		// Used to precheck digital-mailboxes.
		// TODO: When messaging exposes the new precheck endpoint, implement the mapping and the call to messaging. The return
		// value should include which recipients that have an eligible digital-mailbox.
		return true;
	}

}
