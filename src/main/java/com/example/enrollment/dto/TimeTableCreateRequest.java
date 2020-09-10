package com.example.enrollment.dto;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TimeTableCreateRequest {

    private List<Long> courseIds;

    public void validate() {
        List<Long> distinctCourseIds = courseIds.stream()
                .distinct()
                .collect(Collectors.toList());

        if (courseIds.size() != distinctCourseIds.size()) {
            throw new RuntimeException("강의 id 중복");
        }
    }
}
