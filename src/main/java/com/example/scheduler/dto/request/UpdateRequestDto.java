package com.example.scheduler.dto.request;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private String password;
    private String task;
    private String author;
}
