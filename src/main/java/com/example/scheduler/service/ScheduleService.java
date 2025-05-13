package com.example.scheduler.service;


import com.example.scheduler.dto.request.DeleteRequestDto;
import com.example.scheduler.dto.request.ScheduleRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    List<ScheduleResponseDto> findAllSchedule();
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(Long id, UpdateRequestDto updateRequestDto);
    void deleteSchedule(Long id, DeleteRequestDto deleteRequestDto);
}
