package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    //auto_increment
    private Long id;
    private String password;

    private String task;
    private String author;

    //current_timestamp
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

}
