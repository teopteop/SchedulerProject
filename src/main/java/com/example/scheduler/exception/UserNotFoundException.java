package com.example.scheduler.exception;

//authorId 와 매칭되는 user 가 없을 시 발생하는 예외
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
