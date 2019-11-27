package com.telarg.security.data.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ClassifyRequest {

    @Size(max = 150)
    @NotEmpty
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}