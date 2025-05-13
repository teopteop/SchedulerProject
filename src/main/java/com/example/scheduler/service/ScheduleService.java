package com.example.scheduler.service;


import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    List<ScheduleResponseDto> findAllSchedule();
    ScheduleResponseDto findScheduleById(Long id);
}
