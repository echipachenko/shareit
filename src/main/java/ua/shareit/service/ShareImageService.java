package ua.shareit.service;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.shareit.domain.ShareImage;
import ua.shareit.repository.ShareImageRepository;
import ua.shareit.service.storage.IStorage;

@Service
public class ShareImageService {
    private final IStorage storage;
    private final ShareImageRepository shareImageRepository;

    @Autowired
    public ShareImageService(IStorage storage, ShareImageRepository shareImageRepository) {
        this.storage = storage;
        this.shareImageRepository = shareImageRepository;
    }

    public ShareImage shareImage(String fileName, InputStream is) {
        UUID uuid = storage.saveFile(is);
        ShareImage share = new ShareImage();
        share.setUid(uuid);
        share.setFileName(fileName);
        share.setCreated(ZonedDateTime.now());
        share.setExpired(ZonedDateTime.now().plusHours(24));
        shareImageRepository.save(share);
        return share;
    }

    @Transactional(readOnly = true)
    public Optional<ShareImage> findByUid(UUID uuid) {
        return shareImageRepository.findByUid(uuid);
    }
}
