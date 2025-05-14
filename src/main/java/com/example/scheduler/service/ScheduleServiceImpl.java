package com.example.scheduler.service;

import com.example.scheduler.dto.request.DeleteRequestDto;
import com.example.scheduler.dto.request.CreateRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.PasswordResponseDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.exception.PasswordMismatchException;
import com.example.scheduler.exception.ScheduleNotFoundException;
import com.example.scheduler.exception.UserNotFoundException;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(CreateRequestDto cDto) {
        //일치하는 유저 없으면
        scheduleRepository.userIdFindByAuthorId(cDto.getAuthorId()).stream().findAny().orElseThrow(() -> new UserNotFoundException("해당 id와 일치하는 유저가 없습니다."));
        Schedule schedule = new Schedule(cDto.getAuthorId(), cDto.getPassword(), cDto.getTask());
        Long id = scheduleRepository.saveSchedule(schedule);

        return scheduleRepository.findScheduleById(id)
                .stream().findAny().orElseThrow(()-> new ScheduleNotFoundException("해당 id와 일치하는 일정이 없습니다."));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleRepository.findAllSchedule();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id)
                .stream().findAny().orElseThrow(()-> new ScheduleNotFoundException("해당 id와 일치하는 일정이 없습니다."));
    }

    @Override
    public Optional<ScheduleResponseDto> updateSchedule(Long id, UpdateRequestDto uDto) {
        //password 확인하고 해당 id 값과 일치하는 일정이 있는지 확인
        PasswordResponseDto pDto = scheduleRepository.getPasswordDto(id).stream().findAny().orElseThrow(() -> new ScheduleNotFoundException("해당 id와 일치하는 일정이 없습니다."));
        if(!uDto.getPassword().equals(pDto.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        int result = scheduleRepository.updateSchedule(id, uDto);
        return scheduleRepository.findScheduleById(id).stream().findAny();
    }

    @Override
    public void deleteSchedule(Long id, DeleteRequestDto dDto) {
        //password 확인하고 해당 id 값과 일치하는 일정이 있는지 확인
        PasswordResponseDto pDto = scheduleRepository.getPasswordDto(id).stream().findAny().orElseThrow(() -> new ScheduleNotFoundException("해당 id와 일치하는 일정이 없습니다."));
        if(!dDto.getPassword().equals(pDto.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
    }
}
