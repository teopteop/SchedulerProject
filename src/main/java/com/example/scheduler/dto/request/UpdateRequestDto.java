package com.example.scheduler.dto.request;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private Long scheduleId;
    private String password;
    private String task;
}
