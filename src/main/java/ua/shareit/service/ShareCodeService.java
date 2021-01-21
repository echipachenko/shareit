package ua.shareit.service;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.shareit.domain.ShareCode;
import ua.shareit.repository.ShareCodeRepository;
import ua.shareit.web.rest.vm.ShareCodeVM;

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

    public ShareCode save(ShareCodeVM shareCodeVM) {
        ShareCode shareCode = new ShareCode();
        shareCode.setUid(UUID.randomUUID());
        shareCode.setCreated(ZonedDateTime.now());
        shareCode.setExpired(shareCode.getCreated().plusDays(1));
        shareCode.setCode(shareCodeVM.getCode());
        return shareCodeRepository.save(shareCode);
    }
}