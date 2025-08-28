package se.sundsvall.postportalservice.api;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import generated.se.sundsvall.messaging.ConstraintViolationProblem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;

@Validated
@RestController
@Tag(name = "Statistics Resources")
@RequestMapping("/{municipalityId}/statistics")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "502", description = "Bad Gateway", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class StatisticsResource {

	StatisticsResource() {}

	@Operation(summary = "Get general statistics for each department within the municipality", responses = {
		@ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
	})
	@GetMapping("/departments")
	ResponseEntity<Void> getStatisticsByDepartment(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId) {

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@Operation(summary = "Get statistics for a specific department within the municipality", responses = {
		@ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
		@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@GetMapping("/departments/{departmentId}")
	ResponseEntity<Void> getDepartmentStatistics(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@Parameter(name = "departmentId", description = "Department ID", example = "1337") @PathVariable final String departmentId) {

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@Operation(summary = "Get general statistics for each user within the municipality", responses = {
		@ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
	})
	@GetMapping("/users")
	ResponseEntity<Void> getStatisticsByUser(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId) {

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@Operation(summary = "Get statistics for a specific user within the municipality", responses = {
		@ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
		@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@GetMapping("/users/{userId}")
	ResponseEntity<Void> getUserStatistics(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@Parameter(name = "userId", description = "User ID", example = "joe12doe") @PathVariable final String userId) {

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

}
