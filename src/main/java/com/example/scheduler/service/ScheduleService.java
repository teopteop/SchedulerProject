package com.example.scheduler.service;


import com.example.scheduler.dto.request.DeleteRequestDto;
import com.example.scheduler.dto.request.CreateRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.dto.response.ScheduleWithUserNameDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(CreateRequestDto cDto);
    List<ScheduleResponseDto> findAllSchedule();
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleWithUserNameDto> findScheduleByUserId(Long id);
    ScheduleResponseDto updateSchedule(Long id, UpdateRequestDto updateRequestDto);
    void deleteSchedule(Long id, DeleteRequestDto deleteRequestDto);
}
