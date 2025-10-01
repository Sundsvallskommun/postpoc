package se.sundsvall.postportalservice.integration.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.postportalservice.api.model.Statistics;

/**
 * StatisticsRepository tests.
 *
 * @see "/src/test/resources/db/script/testdata.sql for data setup"
 */
@Sql(scripts = {
	"/db/script/testdata.sql"
})
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("junit")
@Import(StatisticsRepository.class)
class StatisticsRepositoryTest {

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Test
	void getDepartmentStatistics_september_2025() {
		var year = "2025";
		var month = "9";

		var result = statisticsRepository.getDepartmentStatisticsByYearAndMonth(year, month);

		assertThat(result).hasSize(5)
			.extracting(
				Statistics::getId,
				Statistics::getName,
				Statistics::getSnailMail,
				Statistics::getDigitalMail,
				Statistics::getDigitalRegisteredLetter,
				Statistics::getSms)
			.containsExactlyInAnyOrder(
				tuple("department1", "Miljöförvaltningen", 3L, 0L, 0L, 0L),
				tuple("department2", "Socialförvaltningen", 0L, 2L, 0L, 1L),
				tuple("department3", "IT-avdelningen", 0L, 0L, 2L, 0L),
				tuple("department4", "HR-avdelningen", 0L, 0L, 0L, 3L),
				tuple("department5", "Kulturförvaltningen", 1L, 1L, 0L, 1L));
	}

	@Test
	void getDepartmentStatistics_august_2025() {
		var year = "2025";
		var month = "8";

		var result = statisticsRepository.getDepartmentStatisticsByYearAndMonth(year, month);

		assertThat(result).hasSize(2).extracting(
			Statistics::getId,
			Statistics::getName,
			Statistics::getSnailMail,
			Statistics::getDigitalMail,
			Statistics::getDigitalRegisteredLetter,
			Statistics::getSms).containsExactlyInAnyOrder(
				tuple("department1", "Miljöförvaltningen", 25L, 5L, 0L, 5L),
				tuple("department2", "Socialförvaltningen", 0L, 20L, 10L, 5L));
	}

	@Test
	void statisticsMapper_mapRow() throws SQLException {
		var departmentId = "123";
		var departmentName = "Test Department";
		var snailMailCount = 10L;
		var digitalMailCount = 20L;
		var digitalRegisteredLetterCount = 5L;
		var smsCount = 15L;

		var resultSet = Mockito.mock(ResultSet.class);
		when(resultSet.getString("department_id")).thenReturn(departmentId);
		when(resultSet.getString("department_name")).thenReturn(departmentName);
		when(resultSet.getLong("snail_mail_count")).thenReturn(snailMailCount);
		when(resultSet.getLong("digital_mail_count")).thenReturn(digitalMailCount);
		when(resultSet.getLong("digital_registered_letter_count")).thenReturn(digitalRegisteredLetterCount);
		when(resultSet.getLong("sms_count")).thenReturn(smsCount);

		var mapper = new StatisticsRepository.StatisticsMapper();

		var result = mapper.mapRow(resultSet, 3);

		assertThat(result).isNotNull().satisfies(statistics -> {
			assertThat(statistics.getId()).isEqualTo(departmentId);
			assertThat(statistics.getName()).isEqualTo(departmentName);
			assertThat(statistics.getSnailMail()).isEqualTo(snailMailCount);
			assertThat(statistics.getDigitalMail()).isEqualTo(digitalMailCount);
			assertThat(statistics.getDigitalRegisteredLetter()).isEqualTo(digitalRegisteredLetterCount);
			assertThat(statistics.getSms()).isEqualTo(smsCount);
		});

		verify(resultSet).getString("department_id");
		verify(resultSet).getString("department_name");
		verify(resultSet).getLong("snail_mail_count");
		verify(resultSet).getLong("digital_mail_count");
		verify(resultSet).getLong("digital_registered_letter_count");
		verify(resultSet).getLong("sms_count");
	}

}
