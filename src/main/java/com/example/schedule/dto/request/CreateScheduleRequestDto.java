package com.example.schedule.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private String title;

    private String contents;

    private String writer;

    private String password;
}
