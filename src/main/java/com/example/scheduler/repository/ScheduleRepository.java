package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;

public interface ScheduleRepository {
    Long saveSchedule(Schedule schedule);
}
