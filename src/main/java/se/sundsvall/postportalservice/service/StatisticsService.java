package se.sundsvall.postportalservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.postportalservice.api.model.Statistics;
import se.sundsvall.postportalservice.integration.db.dao.StatisticsRepository;

@Service
public class StatisticsService {

	private final StatisticsRepository statisticsRepository;

	public StatisticsService(final StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

	public List<Statistics> getDepartmentStatistics(final String year, final String month) {
		return statisticsRepository.getDepartmentStatisticsByYearAndMonth(year, month);
	}

}
