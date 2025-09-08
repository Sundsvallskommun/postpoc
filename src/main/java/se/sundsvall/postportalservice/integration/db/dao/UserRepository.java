package se.sundsvall.postportalservice.integration.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sundsvall.postportalservice.integration.db.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
