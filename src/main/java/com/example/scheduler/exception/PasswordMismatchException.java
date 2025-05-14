package com.example.scheduler.exception;

//비밀번호가 일치하지 않을 때 발생하는 예외
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
