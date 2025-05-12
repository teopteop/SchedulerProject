package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
}
