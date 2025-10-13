package se.sundsvall.postportalservice.service.util;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Problem;

public final class CsvUtil {

	private CsvUtil() {}

	public static List<String> parseCsvToLegalIds(final MultipartFile csvFile) {
		List<String> legalIds = new ArrayList<>();

		try (var reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8))) {

			// Skipping header line
			var line = reader.readLine();

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty()) {
					continue;
				}

				var columns = line.split(";");
				var legalId = columns[0].trim();

				if (!legalId.isEmpty()) {
					legalIds.add(legalId);
				}
			}
		} catch (IOException e) {
			throw Problem.valueOf(INTERNAL_SERVER_ERROR, "Error reading CSV file: " + e.getMessage());
		}

		return legalIds;
	}
}
