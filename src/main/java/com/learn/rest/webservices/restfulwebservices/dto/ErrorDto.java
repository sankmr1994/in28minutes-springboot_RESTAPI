package com.learn.rest.webservices.restfulwebservices.dto;

import java.time.LocalDateTime;

public class ErrorDto {
    private String error;

    private LocalDateTime timeStamp;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
