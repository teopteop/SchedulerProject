package com.example.scheduler.repository;

import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.PasswordResponseDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.dto.response.ScheduleWithUserNameDto;
import com.example.scheduler.dto.response.UserIdResponseDto;
import com.example.scheduler.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Long saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    List<ScheduleResponseDto> findScheduleById(Long id);

    List<ScheduleWithUserNameDto> findScheduleByUserId(Long id);

    int updateSchedule(Long id, UpdateRequestDto updateRequestDto);

    List<PasswordResponseDto> getPasswordDto(Long id);

    int deleteSchedule(Long id);

    List<UserIdResponseDto> userIdFindByAuthorId(Long id);
}
