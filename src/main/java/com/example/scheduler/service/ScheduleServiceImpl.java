package com.example.scheduler.service;

import com.example.scheduler.dto.request.DeleteRequestDto;
import com.example.scheduler.dto.request.CreateRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.dto.response.UserIdResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.exception.UserNotFoundException;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(CreateRequestDto cDto) {
        scheduleRepository.userIdFindByAuthorId(cDto.getAuthorId()).stream().findAny().orElseThrow(() -> new UserNotFoundException("해당 id와 일치하는 유저가 없습니다."));
        Schedule schedule = new Schedule(cDto.getPassword(), cDto.getTask(), cDto.getAuthorId());
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
        //password check
        if(updateRequestDto.getPassword().equals(scheduleRepository.getPasswordDto(id).getPassword())){
            scheduleRepository.updateSchedule(id, updateRequestDto);
        };
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    public void deleteSchedule(Long id, DeleteRequestDto deleteRequestDto) {
        //password check
        if(deleteRequestDto.getPassword().equals(scheduleRepository.getPasswordDto(id).getPassword())){
            scheduleRepository.deleteSchedule(id);
        }
    }
}
