package ua.shareit.web.rest.share;

import java.net.URI;
import java.net.URLConnection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import io.github.jhipster.web.util.ResponseUtil;
import ua.shareit.domain.ShareImage;
import ua.shareit.service.ShareImageService;
import ua.shareit.service.storage.IStorage;

@RestController
@RequestMapping("/api/share/image")
public class ShareImageResource {

    private final ShareImageService shareImageService;
    private final IStorage storage;

    public ShareImageResource(ShareImageService shareImageService, IStorage storage) {
        this.shareImageService = shareImageService;
        this.storage = storage;
    }

    @PostMapping
    public ResponseEntity<ShareImage> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        ShareImage result = shareImageService.shareImage(file.getOriginalFilename(), file.getInputStream());
        return ResponseEntity.created(new URI("/api/share/image/" + result.getUid())).body(result);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ShareImage> getByUid(@PathVariable("uid") String uid) {
        return ResponseUtil.wrapOrNotFound(shareImageService.findByUid(UUID.fromString(uid)));
    }

    @GetMapping("/{uid}/image")
    public ResponseEntity<StreamingResponseBody> getImageByUid(@PathVariable("uid") String uid) {
        Optional<ShareImage> sh = shareImageService.findByUid(UUID.fromString(uid));
        if (!sh.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ShareImage shareImage = sh.get();
        String mimeType = URLConnection.guessContentTypeFromName(shareImage.getFileName());
        return ResponseEntity
            .ok()
            .header("Content-Disposition", String.format("attachment; filename=\"%s\"", shareImage.getFileName()))
            .header("Content-Type", mimeType)
            .body(os -> storage.readFileToOutputStream(os, shareImage.getUid()));
    }
}
