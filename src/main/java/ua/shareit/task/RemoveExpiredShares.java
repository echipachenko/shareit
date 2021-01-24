package ua.shareit.task;

import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.shareit.repository.ShareCodeRepository;

@Service
@Transactional
public class RemoveExpiredShares {
    private final Logger log = LoggerFactory.getLogger(RemoveExpiredShares.class);
    private final ShareCodeRepository shareCodeRepository;

    @Autowired
    public RemoveExpiredShares(ShareCodeRepository shareCodeRepository) {
        this.shareCodeRepository = shareCodeRepository;
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void removeExpiredCodeShares() {
        long removed = shareCodeRepository.deleteByExpiredBefore(ZonedDateTime.now());
        if (removed > 0) {
            log.info("{} elements were removed", removed);
        }
    }
}
