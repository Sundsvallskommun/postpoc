package se.sundsvall.postportalservice.service;

import static org.zalando.problem.Status.BAD_GATEWAY;
import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import se.sundsvall.dept44.support.Identifier;
import se.sundsvall.postportalservice.integration.employee.EmployeeIntegration;
import se.sundsvall.postportalservice.integration.employee.EmployeeUtil;

@Service
public class EmployeeService {

	private final EmployeeIntegration employeeIntegration;

	public EmployeeService(final EmployeeIntegration employeeIntegration) {
		this.employeeIntegration = employeeIntegration;
	}

	public SentBy getSentBy(final String municipalityId) {
		var username = Identifier.get().getValue();
		var personData = employeeIntegration.getPortalPersonData(municipalityId, username)
			.orElseThrow(() -> Problem.valueOf(BAD_GATEWAY, "Failed to retrieve employee data for user [%s]".formatted(username)));
		var department = EmployeeUtil.parseOrganizationString(personData.getOrgTree())
			.orElseThrow(() -> Problem.valueOf(INTERNAL_SERVER_ERROR, "Failed to parse organization from employee data"));

		return new SentBy(username, department.identifier(), department.name());
	}

	public record SentBy(String userName, String departmentId, String departmentName) {
	}

}
