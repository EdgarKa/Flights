package com.example.flights.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends Exception {

    private final String message;
    private final HttpStatus httpStatus;
    private final long timestamp;

    public AppException(String message, HttpStatus httpStatus, long timestamp) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public AppException(String message, HttpStatus status) {
        this(message, status, System.currentTimeMillis());
    }

    public AppException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
