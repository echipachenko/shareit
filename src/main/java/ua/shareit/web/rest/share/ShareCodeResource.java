package ua.shareit.web.rest.share;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ua.shareit.domain.ShareCode;
import ua.shareit.service.ShareCodeService;
import ua.shareit.service.google.ReCaptchaService;
import ua.shareit.web.rest.vm.ShareCodeVM;

/**
 * REST controller for managing {@link ua.shareit.domain.ShareCode}.
 */
@RestController
@RequestMapping("/api/share/code")
public class ShareCodeResource {

    private static final int UUID_LENGTH = 36;
    private final Logger log = LoggerFactory.getLogger(ShareCodeResource.class);

    private static final String ENTITY_NAME = "shareCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShareCodeService shareCodeService;
    private final ReCaptchaService reCaptchaService;

    public ShareCodeResource(ShareCodeService shareCodeService, ReCaptchaService reCaptchaService) {
        this.shareCodeService = shareCodeService;
        this.reCaptchaService = reCaptchaService;
    }

    @PostMapping
    public ResponseEntity<ShareCode> createShareCode(@Valid @RequestBody ShareCodeVM shareCode) throws URISyntaxException {
        log.debug("REST request to save ShareCode : {}", shareCode);
        reCaptchaService.validate(shareCode.getRecaptchaUserResponse());
        ShareCode result = shareCodeService.save(shareCode.getCode());
        return ResponseEntity.created(new URI("/api/share/code/" + result.getUid()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUid().toString()))
            .body(result);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ShareCode> getByUid(@PathVariable("uid") String uid) {
        if (StringUtils.length(uid) != UUID_LENGTH) {
            return ResponseEntity.notFound().build();
        }
        return ResponseUtil.wrapOrNotFound(shareCodeService.findByUid(UUID.fromString(uid)));
    }
}
