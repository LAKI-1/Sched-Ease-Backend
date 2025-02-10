package com.sched_ease.backend.exception;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String errorType;
    private String message;

    public ErrorDetails(Date timestamp, String errorType, String message) {
        super();
        this.timestamp = timestamp;
        this.errorType = errorType;
        this.message = message;
    }

    // Getters and setters
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
