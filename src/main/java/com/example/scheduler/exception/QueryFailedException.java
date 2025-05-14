package com.example.scheduler.exception;

public class QueryFailedException extends RuntimeException {
    public QueryFailedException(String message) {
        super(message);
    }
}
