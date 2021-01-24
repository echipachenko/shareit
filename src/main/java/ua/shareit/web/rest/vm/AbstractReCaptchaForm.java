package ua.shareit.web.rest.vm;

public abstract class AbstractReCaptchaForm {
    private String recaptchaUserResponse;

    public String getRecaptchaUserResponse() {
        return recaptchaUserResponse;
    }

    public void setRecaptchaUserResponse(String recaptchaUserResponse) {
        this.recaptchaUserResponse = recaptchaUserResponse;
    }
}
