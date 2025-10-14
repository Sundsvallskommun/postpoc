package se.sundsvall.postportalservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static se.sundsvall.postportalservice.TestDataFactory.MUNICIPALITY_ID;

import generated.se.sundsvall.employee.PortalPersonData;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Problem;
import se.sundsvall.dept44.support.Identifier;
import se.sundsvall.postportalservice.integration.employee.EmployeeIntegration;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	private static final String USERNAME = "username";

	@Mock
	private EmployeeIntegration employeeIntegrationMock;

	@InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	void setup() {
		var identifier = Identifier.create()
			.withType(Identifier.Type.AD_ACCOUNT)
			.withValue(USERNAME)
			.withTypeString("AD_ACCOUNT");
		Identifier.set(identifier);
	}

	@AfterEach
	void verifyNoUnexpectedMockInteractions() {
		verifyNoMoreInteractions(employeeIntegrationMock);
	}

	@Test
	void getSentBy() {
		var username = Identifier.get().getValue();

		var personData = new PortalPersonData()
			.orgTree("2|42|En man som heter Ove¤3|880|Sunes sommar¤4|1234|Solsidan¤5|8456|Sällskapsresan¤6|7894|Tomten är far till alla barnen");
		when(employeeIntegrationMock.getPortalPersonData(MUNICIPALITY_ID, username)).thenReturn(Optional.of(personData));

		var result = employeeService.getSentBy(MUNICIPALITY_ID);

		assertThat(result).isNotNull().isInstanceOf(EmployeeService.SentBy.class);
		assertThat(result.userName()).isEqualTo(username);
		assertThat(result.departmentId()).isEqualTo("42");
		assertThat(result.departmentName()).isEqualTo("En man som heter Ove");
	}

	@Test
	void getSentBy_invalidOrgTree() {
		var username = Identifier.get().getValue();

		var personData = new PortalPersonData()
			.orgTree("invalid-org-tree-format");
		when(employeeIntegrationMock.getPortalPersonData(MUNICIPALITY_ID, username)).thenReturn(Optional.of(personData));

		assertThatThrownBy(() -> employeeService.getSentBy(MUNICIPALITY_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Internal Server Error: Failed to parse organization from employee data");

		verify(employeeIntegrationMock).getPortalPersonData(MUNICIPALITY_ID, username);
	}

	@Test
	void getSentBy_employeeNotFound() {
		var username = Identifier.get().getValue();

		when(employeeIntegrationMock.getPortalPersonData(MUNICIPALITY_ID, username)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> employeeService.getSentBy(MUNICIPALITY_ID))
			.isInstanceOf(Problem.class)
			.hasMessage("Bad Gateway: Failed to retrieve employee data for user [%s]".formatted(username));

		verify(employeeIntegrationMock).getPortalPersonData(MUNICIPALITY_ID, username);

	}

}
