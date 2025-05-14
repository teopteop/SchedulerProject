package com.example.scheduler.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleWithUserNameDto {

    private Long scheduleId;
    private Long authorId;
    private String name;
    private String task;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
}
