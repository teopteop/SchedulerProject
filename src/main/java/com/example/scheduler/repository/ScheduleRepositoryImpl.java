package com.example.scheduler.repository;

import com.example.scheduler.dto.request.UpdateRequestDto;
import com.example.scheduler.dto.response.PasswordResponseDto;
import com.example.scheduler.dto.response.ScheduleResponseDto;
import com.example.scheduler.dto.response.UserIdResponseDto;
import com.example.scheduler.entity.Schedule;
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
        simpleJdbcInsert.withTableName("schedule").usingColumns("password","task","author") //컬럼지정 추가
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("password", schedule.getPassword());
        params.put("task", schedule.getTask());
        params.put("author", schedule.getAuthor());

        return simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params)).longValue();
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleFindRowMapper());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return jdbcTemplate.query("select * from schedule where id=?", scheduleFindRowMapper(), id)
                .stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "requested ID does not exist."));
    }

    @Override
    public int updateSchedule(Long id, UpdateRequestDto updateRequestDto) {
        return jdbcTemplate.update("update schedule set task=?, author=? where id=?", updateRequestDto.getTask(), updateRequestDto.getAuthor(), id);
    }

    @Override
    public void deleteSchedule(Long id) {
        int rs = jdbcTemplate.update("delete from schedule where id=?", id);
    }

    //password 가 필요할 시 사용
    @Override
    public PasswordResponseDto getPasswordDto(Long id) {
        return jdbcTemplate.query("select id, password from schedule where id=?", passwordMapper(), id)
                .stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "requested ID does not exist."));
    }

    @Override
    public List<UserIdResponseDto> userIdFindByAuthorId(Long id) {
        return jdbcTemplate.query("select * from user where id=?", userIdMapper(), id);
    }

    private RowMapper<ScheduleResponseDto> scheduleFindRowMapper() {
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
