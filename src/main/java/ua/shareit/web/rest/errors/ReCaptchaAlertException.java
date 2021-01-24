package ua.shareit.web.rest.errors;

public class ReCaptchaAlertException extends BadRequestAlertException {
    public ReCaptchaAlertException() {
        super("ReCaptcha check failed", "recaptcha", "failed");
    }
}
