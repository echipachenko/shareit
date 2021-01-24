package ua.shareit.service.google;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

import ua.shareit.config.ApplicationProperties;
import ua.shareit.service.exception.ReCaptchaException;

@Service
public class ReCaptchaService {
    private static final Logger log = LoggerFactory.getLogger(ReCaptchaService.class);
    private static final String API = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    private final RestTemplate restTemplate;
    private final ApplicationProperties.Recaptcha properties;


    @Autowired
    public ReCaptchaService(RestTemplate restTemplate, ApplicationProperties props) {
        this.restTemplate = restTemplate;
        this.properties = props.getRecaptcha();
    }

    @PostConstruct
    public void verify() {
        if (properties.isEnabled()) {
            if (StringUtils.isBlank(properties.getSecret())) {
                log.error("Re-captcha enabled, but secret key is empty, please specify the secret key!");
                log.error("Disabling re-captcha...");
                properties.setEnabled(false);
            } else {
                log.info("Re-captcha validation enabled");
            }
        } else {
            log.warn("Re-captcha validation disabled");
        }
    }

    public void validate(String userResponse) {
        if (!properties.isEnabled()) {
            return;
        }
        if (StringUtils.isBlank(userResponse)) {
            throw new ReCaptchaException();
        }

        String url = String.format(API, properties.getSecret(), userResponse);
        ValidateResponse body = restTemplate.postForObject(url, null, ValidateResponse.class);
        if (body == null || !body.isSuccess()) {
            throw new ReCaptchaException();
        }
    }

    public static class ValidateResponse {
        private boolean success;
        @JsonProperty("challenge_ts")
        private LocalDateTime challengeTs;
        private String hostname;
        @JsonProperty("error-codes")
        private List<String> errorCodes;

        public boolean isSuccess() {
            return success;
        }

        public ValidateResponse setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public LocalDateTime getChallengeTs() {
            return challengeTs;
        }

        public ValidateResponse setChallengeTs(LocalDateTime challengeTs) {
            this.challengeTs = challengeTs;
            return this;
        }

        public String getHostname() {
            return hostname;
        }

        public ValidateResponse setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public List<String> getErrorCodes() {
            return errorCodes;
        }

        public ValidateResponse setErrorCodes(List<String> errorCodes) {
            this.errorCodes = errorCodes;
            return this;
        }
    }
}
