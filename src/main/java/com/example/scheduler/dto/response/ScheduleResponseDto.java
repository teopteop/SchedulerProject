package com.example.scheduler.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    private Long authorId;
    private String task;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

}
