package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Schedule {

    //auto_increment
    private Long scheduleId;
    private Long authorId;
    private String password;
    private String task;

    //current_timestamp
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

}
