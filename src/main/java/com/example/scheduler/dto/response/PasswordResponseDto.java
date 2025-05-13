package com.example.scheduler.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordResponseDto {
    private Long scheduleId;
    private String password;
}
