package ua.shareit.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

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
import ua.shareit.web.rest.vm.ShareCodeVM;

/**
 * REST controller for managing {@link ua.shareit.domain.ShareCode}.
 */
@RestController
@RequestMapping("/api")
public class ShareCodeResource {

    private final Logger log = LoggerFactory.getLogger(ShareCodeResource.class);

    private static final String ENTITY_NAME = "shareCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShareCodeService shareCodeService;

    public ShareCodeResource(ShareCodeService shareCodeService) {
        this.shareCodeService = shareCodeService;
    }

    @PostMapping("/share-codes")
    public ResponseEntity<ShareCode> createShareCode(@Valid @RequestBody ShareCodeVM shareCode) throws URISyntaxException {
        log.debug("REST request to save ShareCode : {}", shareCode);
        ShareCode result = shareCodeService.save(shareCode.getCode());
        return ResponseEntity.created(new URI("/api/share-codes/" + result.getUid()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUid().toString()))
                .body(result);
    }

    @GetMapping("/share-codes/{uid}")
    public ResponseEntity<ShareCode> getByUid(@PathVariable("uid") UUID uid) {
        return ResponseUtil.wrapOrNotFound(shareCodeService.findByUid(uid));
    }
}
