package ua.shareit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Shareit.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private final Recaptcha recaptcha = new Recaptcha();

    public Recaptcha getRecaptcha() {
        return recaptcha;
    }

    public static class Recaptcha {
        private boolean enabled = false;
        private String secret = "";

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
