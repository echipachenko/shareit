package ua.shareit.service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.shareit.domain.ShareCode;
import ua.shareit.repository.ShareCodeRepository;

/**
 * Service Implementation for managing {@link ShareCode}.
 */
@Service
@Transactional
public class ShareCodeService {

    private final ShareCodeRepository shareCodeRepository;

    public ShareCodeService(ShareCodeRepository shareCodeRepository) {
        this.shareCodeRepository = shareCodeRepository;
    }

    public ShareCode save(String code) {
        ShareCode shareCode = new ShareCode();
        shareCode.setUid(UUID.randomUUID());
        shareCode.setCreated(ZonedDateTime.now());
        shareCode.setExpired(shareCode.getCreated().plusDays(1));
        shareCode.setCode(code);
        return shareCodeRepository.save(shareCode);
    }

    @Transactional(readOnly = true)
    public Optional<ShareCode> findByUid(UUID uid) {
        return shareCodeRepository.findByUid(uid);
    }
}
