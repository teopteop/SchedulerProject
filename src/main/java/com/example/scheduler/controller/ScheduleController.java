package com.example.scheduler.controller;

import com.example.scheduler.dto.request.DeleteRequestDto;
import com.example.scheduler.dto.request.ScheduleRequestDto;
import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    //일정생성 등록 id값 리턴
    @PostMapping
    public ResponseEntity<Long> saveSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto).getId(), HttpStatus.CREATED);
    }

    //일정 전체조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(){
        return new ResponseEntity<>(scheduleService.findAllSchedule(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto updateRequestDto
    ){
      return new ResponseEntity<>(scheduleService.updateSchedule(id, updateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody DeleteRequestDto deleteRequestDto
    ){
        scheduleService.deleteSchedule(id, deleteRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
