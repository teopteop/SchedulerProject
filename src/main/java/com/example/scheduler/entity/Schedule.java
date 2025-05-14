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

    //save를 위한 필수 필드를 담은 생성자
    public Schedule(Long authorId, String password, String task) {
        this.authorId = authorId;
        this.password = password;
        this.task = task;
    }
}
