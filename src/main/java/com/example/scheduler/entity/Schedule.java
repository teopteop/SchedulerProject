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

    public Schedule(String password, String task, String author){
        this.password = password;
        this.task = task;
        this.author = author;
    }

}
