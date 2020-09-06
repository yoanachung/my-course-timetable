package com.example.enrollment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TimeTableCreateRequest {

    private List<Long> courseIds;
}
