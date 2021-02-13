package ua.shareit.web.rest.share;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.jhipster.web.util.ResponseUtil;
import ua.shareit.domain.ShareImage;
import ua.shareit.service.ShareImageService;

@RestController
@RequestMapping("/api/share-images")
public class ShareImageResource {

    private final ShareImageService shareImageService;

    public ShareImageResource(ShareImageService shareImageService) {
        this.shareImageService = shareImageService;
    }

    @PostMapping
    public ResponseEntity<ShareImage> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        ShareImage result = shareImageService.shareImage(file.getOriginalFilename(), file.getInputStream());
        return ResponseEntity.created(new URI("/api/share-images/" + result.getUid())).body(result);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ShareImage> getByUid(@PathVariable("uid") String uid){
        return ResponseUtil.wrapOrNotFound(shareImageService.findByUid(UUID.fromString(uid)));
    }
}
