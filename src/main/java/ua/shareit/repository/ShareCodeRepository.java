package ua.shareit.repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.shareit.domain.ShareCode;

@Repository
public interface ShareCodeRepository extends JpaRepository<ShareCode, Long> {

    long deleteByExpiredBefore(ZonedDateTime now);

    Optional<ShareCode> findByUid(UUID uid);
}
