package com.example.scheduler.exception;

//id 값과 일치하는 schedule 이 없을 때 발생하는 예외
public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
