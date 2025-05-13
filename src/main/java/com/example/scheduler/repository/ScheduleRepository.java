package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Long saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedule();
}
