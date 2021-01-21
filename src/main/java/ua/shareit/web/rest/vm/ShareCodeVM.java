package ua.shareit.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShareCodeVM {
    @NotNull
    @Size(max = 1024 * 1024) //1MB
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
