package ua.shareit.repository;

import ua.shareit.domain.ShareCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShareCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShareCodeRepository extends JpaRepository<ShareCode, Long> {
}
