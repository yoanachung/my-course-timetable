package com.example.enrollment.dto;

import com.example.enrollment.domain.timetable.TimeTableList;
import lombok.Getter;

import java.util.List;

@Getter
public class TimeTableCreateResponse {

    private String hash;
    private List<Long> courseIds;

    public TimeTableCreateResponse(TimeTableList timeTableList) {
        this.hash = timeTableList.getHash();
        this.courseIds = timeTableList.getCourseIds();
    }
}
