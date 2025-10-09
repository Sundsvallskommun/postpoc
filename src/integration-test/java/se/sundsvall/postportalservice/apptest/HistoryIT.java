package se.sundsvall.postportalservice.apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.postportalservice.Application;

@Sql(scripts = {
	"/db/script/truncate.sql",
	"/db/script/testdata.sql"
})
@WireMockAppTestSuite(files = "classpath:/HistoryIT/", classes = Application.class)
class HistoryIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	@Test
	void test01_getUserMessages() {
		setupCall()
			.withServicePath("/2281/history/users/user1/messages")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_getMessageDetails() {
		setupCall()
			.withServicePath("/2281/history/users/user1/messages/message101")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_getSigningInformation() {
		setupCall()
			.withServicePath("/2281/history/messages/message3/signinginfo") // message3 is a DIGITAL_REGISTERED_LETTER in the test data
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
