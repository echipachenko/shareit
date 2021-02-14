package ua.shareit.task;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.shareit.domain.ShareImage;
import ua.shareit.repository.ShareCodeRepository;
import ua.shareit.repository.ShareImageRepository;
import ua.shareit.service.storage.IStorage;

@Service
@Transactional
public class RemoveExpiredShares {
    private final Logger log = LoggerFactory.getLogger(RemoveExpiredShares.class);
    private final ShareCodeRepository shareCodeRepository;
    private final ShareImageRepository shareImageRepository;
    private final IStorage storage;

    @Autowired
    public RemoveExpiredShares(ShareCodeRepository shareCodeRepository, ShareImageRepository shareImageRepository, IStorage storage) {
        this.shareCodeRepository = shareCodeRepository;
        this.shareImageRepository = shareImageRepository;
        this.storage = storage;
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void removeExpiredCodeShares() {
        long removed = shareCodeRepository.deleteByExpiredBefore(ZonedDateTime.now());
        if (removed > 0) {
            log.info("{} elements were removed", removed);
        }
    }

    @Scheduled(fixedDelay = 60 * 1000, initialDelay = 10 * 1000)
    public void removeExpiredImageShares() {
        List<ShareImage> expired = shareImageRepository.findByExpiredLessThan(ZonedDateTime.now());
        expired.forEach(sh -> {
            storage.deleteByUuid(sh.getUid());
            shareImageRepository.delete(sh);
        });

        if (!expired.isEmpty()) {
            log.info("{} image shares were removed", expired.size());
        }
    }
}
