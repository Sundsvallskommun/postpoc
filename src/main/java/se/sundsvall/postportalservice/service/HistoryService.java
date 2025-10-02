package se.sundsvall.postportalservice.service;

import static org.zalando.problem.Status.NOT_FOUND;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.postportalservice.api.model.MessageDetails;
import se.sundsvall.postportalservice.api.model.Messages;
import se.sundsvall.postportalservice.integration.db.dao.MessageRepository;
import se.sundsvall.postportalservice.service.mapper.HistoryMapper;

@Service
public class HistoryService {

	private final MessageRepository messageRepository;
	private final HistoryMapper historyMapper;

	public HistoryService(final MessageRepository messageRepository, final HistoryMapper historyMapper) {
		this.messageRepository = messageRepository;
		this.historyMapper = historyMapper;
	}

	public Messages getUserMessages(final String municipalityId, final String userId, final Pageable pageable) {
		var page = messageRepository.findAllByMunicipalityIdAndUser_Id(municipalityId, userId, pageable);

		return Messages.create()
			.withMetaData(PagingAndSortingMetaData.create().withPageData(page))
			.withMessages(historyMapper.toMessageList(page.getContent()));
	}

	public MessageDetails getMessageDetails(final String municipalityId, final String userId, final String messageId) {
		return messageRepository.findByMunicipalityIdAndIdAndUser_Id(municipalityId, messageId, userId)
			.map(historyMapper::toMessageDetails)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "Message with id '%s' and municipalityId '%s' for user with id '%s' not found".formatted(messageId, municipalityId, userId)));
	}
}
