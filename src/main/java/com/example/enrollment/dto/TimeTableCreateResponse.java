package com.example.enrollment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TimeTableCreateResponse {

    private String token;
    private List<Long> courseIds;
}
