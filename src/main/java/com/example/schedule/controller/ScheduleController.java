package com.example.schedule.controller;

import com.example.schedule.dto.request.CreateScheduleRequestDto;
import com.example.schedule.dto.response.CreateScheduleResponseDto;
import com.example.schedule.dto.response.GetScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public CreateScheduleResponseDto createSchedule(
            @RequestBody CreateScheduleRequestDto requestDto
    ) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping
    public List<GetScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

}
