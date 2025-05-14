package com.example.scheduler.dto.request;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private Long authorId;
    private String password;
    private String task;
}
