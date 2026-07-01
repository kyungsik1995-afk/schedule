package com.example.schedule.service;

import com.example.schedule.dto.request.CreateScheduleRequestDto;
import com.example.schedule.dto.request.DeleteScheduleRequestDto;
import com.example.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.schedule.dto.response.CreateScheduleResponseDto;
import com.example.schedule.dto.response.GetScheduleResponseDto;
import com.example.schedule.dto.response.UpdateScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public CreateScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getWriter(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    public List<GetScheduleResponseDto> getSchedules(String writer) {

        List<Schedule> schedules;

        // writer 값이 있으면 → 작성자 기준 조회
        if (writer != null && !writer.isEmpty()) {
            schedules = scheduleRepository.findByWriterOrderByModifiedAtDesc(writer);
        }
        // writer 없으면 → 전체 조회
        else {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }

        List<GetScheduleResponseDto> responseDtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            responseDtos.add(
                    new GetScheduleResponseDto(
                            schedule.getId(),
                            schedule.getTitle(),
                            schedule.getContents(),
                            schedule.getWriter(),
                            schedule.getCreatedAt(),
                            schedule.getModifiedAt()
                    )
            );
        }

        return responseDtos;
    }
    public GetScheduleResponseDto getSchedule(Long id) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        return new GetScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    public UpdateScheduleResponseDto updateSchedule(
            Long id,
            UpdateScheduleRequestDto requestDto
    ) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(
                requestDto.getTitle(),
                requestDto.getWriter()
        );

        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return new UpdateScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTitle(),
                updatedSchedule.getContents(),
                updatedSchedule.getWriter(),
                updatedSchedule.getCreatedAt(),
                updatedSchedule.getModifiedAt()
        );
    }

    public void deleteSchedule(
            Long id,
            DeleteScheduleRequestDto requestDto
    ) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }
}
