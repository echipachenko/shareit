package ua.shareit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.shareit.domain.ShareImage;

/**
 * Spring Data  repository for the ShareImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShareImageRepository extends JpaRepository<ShareImage, Long> {
}
