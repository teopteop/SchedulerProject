package com.example.scheduler.service;

import com.example.scheduler.dto.request.ScheduleRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.PasswordResponseDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
         Schedule schedule = new Schedule(dto.getPassword(), dto.getTask(), dto.getAuthor());
        return new ScheduleResponseDto(scheduleRepository.saveSchedule(schedule));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleRepository.findAllSchedule();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, UpdateRequestDto updateRequestDto) {
        if(updateRequestDto.getPassword().equals(scheduleRepository.getPasswordDto(id).getPassword())){
            scheduleRepository.updateSchedule(id, updateRequestDto);
        };
        return scheduleRepository.findScheduleById(id);
    }



}
