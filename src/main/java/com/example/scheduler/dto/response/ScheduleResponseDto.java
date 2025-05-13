package com.example.scheduler.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    //저장 시 id값만 리턴
    public ScheduleResponseDto(Long id){
        this.id = id;
    }

}
