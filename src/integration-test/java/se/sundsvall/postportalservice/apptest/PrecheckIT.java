package se.sundsvall.postportalservice.apptest;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.postportalservice.Application;

@WireMockAppTestSuite(files = "classpath:/PrecheckIT/", classes = Application.class)
class PrecheckIT extends AbstractAppTest {

	private static final String SERVICE_PATH = "/2281/dept44/precheck";
	private static final String REQUEST_FILE = "request.json";
	private static final String RESPONSE_FILE = "response.json";

	@Test
	void test01_precheck_ok() {
		setupCall()
			.withHttpMethod(POST)
			.withServicePath(SERVICE_PATH)
			.withContentType(APPLICATION_JSON)
			.withRequest(REQUEST_FILE)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_precheck_missingOrganizationNumber() {
		setupCall()
			.withHttpMethod(POST)
			.withServicePath(SERVICE_PATH)
			.withContentType(APPLICATION_JSON)
			.withRequest(REQUEST_FILE)
			.withExpectedResponseStatus(NOT_FOUND)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
