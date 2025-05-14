package com.example.scheduler.repository;

import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.PasswordResponseDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.dto.response.ScheduleWithUserNameDto;
import com.example.scheduler.dto.response.UserIdResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.exception.ScheduleNotFoundException;
import com.example.scheduler.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedule").usingColumns("authorId","password","task") //컬럼지정 추가
                .usingGeneratedKeyColumns("scheduleId");

        Map<String, Object> params = new HashMap<>();
        params.put("authorId", schedule.getAuthorId());
        params.put("password", schedule.getPassword());
        params.put("task", schedule.getTask());


        return simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params)).longValue();
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleMapper());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleById(Long id) {
        return jdbcTemplate.query("select * from schedule where scheduleId=?", scheduleMapper(), id);
    }

    @Override
    public int updateSchedule(Long id, UpdateRequestDto updateRequestDto) {
        return jdbcTemplate.update("update schedule set task=?, authorId=? where scheduleId=?", updateRequestDto.getTask(), updateRequestDto.getAuthorId(), id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where scheduleId=?", id);
    }

    //password 가 필요할 시 사용
    @Override
    public List<PasswordResponseDto> getPasswordDto(Long id) {
        return jdbcTemplate.query("select scheduleId, password from schedule where scheduleId=?", passwordMapper(), id);
    }

    //user 를 조회하는 메서드
    @Override
    public List<UserIdResponseDto> userIdFindByAuthorId(Long id) {
        return jdbcTemplate.query("select * from user where userId=?", userIdMapper(), id);
    }

    private RowMapper<ScheduleResponseDto> scheduleMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getLong("authorId"),
                        rs.getString("task"),
                        rs.getTimestamp("createDate").toLocalDateTime(),
                        rs.getTimestamp("lastModifiedDate").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<ScheduleWithUserNameDto> scheduleWithUserNameMapper() {
        return new RowMapper<ScheduleWithUserNameDto>() {
            @Override
            public ScheduleWithUserNameDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleWithUserNameDto(
                        rs.getLong("scheduleId"),
                        rs.getLong("authorId"),
                        rs.getString("name"),
                        rs.getString("task"),
                        rs.getTimestamp("createDate").toLocalDateTime(),
                        rs.getTimestamp("lastModifiedDate").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<PasswordResponseDto> passwordMapper() {
        return new RowMapper<PasswordResponseDto>() {
            @Override
            public PasswordResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PasswordResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("password")
                );
            }
        };
    }

    private RowMapper<UserIdResponseDto> userIdMapper() {
        return new RowMapper<UserIdResponseDto>() {
            @Override
            public UserIdResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserIdResponseDto(
                        rs.getLong("userId")
                );
            }
        };
    }

}
