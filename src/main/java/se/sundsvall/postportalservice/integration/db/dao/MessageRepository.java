package se.sundsvall.postportalservice.integration.db.dao;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sundsvall.postportalservice.integration.db.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {

	Page<MessageEntity> findAllByMunicipalityIdAndUser_Id(final String municipalityId, final String userId, final Pageable pageable);

	Optional<MessageEntity> findByMunicipalityIdAndIdAndUser_Id(final String municipalityId, final String messageId, final String userId);

}
