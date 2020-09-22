package com.project.demo.controller.resources;

import java.text.MessageFormat;

public class MessageResource {
    private String message;

    public MessageResource() {
    }

    public MessageResource(String message) {
        this.message = message;
    }

    public MessageResource withParams(Object... params) {
        this.message = MessageFormat.format(this.message, params);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
