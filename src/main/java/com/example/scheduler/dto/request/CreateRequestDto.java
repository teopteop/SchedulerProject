package com.example.scheduler.dto.request;

import lombok.Getter;

@Getter
public class CreateRequestDto {

    private Long scheduleId;
    private Long authorId;

    private String task;
    private String password;

}
