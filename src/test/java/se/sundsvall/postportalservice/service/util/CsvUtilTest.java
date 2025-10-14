package se.sundsvall.postportalservice.service.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Problem;
import se.sundsvall.dept44.test.annotation.resource.Load;
import se.sundsvall.dept44.test.extension.ResourceLoaderExtension;

@ExtendWith(ResourceLoaderExtension.class)
class CsvUtilTest {

	@Test
	void parseCsvToLegalIds(@Load(value = "/testfile/legalIds.csv") final String csv) throws IOException {
		var multipartFileMock = Mockito.mock(MultipartFile.class);

		when(multipartFileMock.getInputStream()).thenReturn(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));
		when(multipartFileMock.getContentType()).thenReturn("text/csv");
		when(multipartFileMock.getOriginalFilename()).thenReturn("legalIds.csv");

		var result = CsvUtil.parseCsvToLegalIds(multipartFileMock);

		assertThat(result).containsExactlyInAnyOrder(
			"201901012391", "201901022382", "201901032399",
			"201901042380", "201901052397", "201901062388");
	}

	@Test
	void parseCsvToLegalId_throws() throws IOException {
		var multipartFileMock = Mockito.mock(MultipartFile.class);

		var customExceptionMessage = "Test exception";
		when(multipartFileMock.getInputStream()).thenThrow(new IOException(customExceptionMessage));

		assertThatThrownBy(() -> CsvUtil.parseCsvToLegalIds(multipartFileMock))
			.isInstanceOf(Problem.class)
			.hasMessageContaining("Internal Server Error: Error reading CSV file: %s".formatted(customExceptionMessage));
	}

}
