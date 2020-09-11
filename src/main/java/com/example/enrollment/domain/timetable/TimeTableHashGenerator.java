package com.example.enrollment.domain.timetable;

import com.example.enrollment.domain.ProjectConstant;
import com.example.enrollment.util.HashUtil;

import java.util.List;
import java.util.stream.Collectors;

public class TimeTableHashGenerator {

    private TimeTableHashGenerator() {}

    public static String generate(List<Long> courseIds) {
        String courseIdsStr = courseIds.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(ProjectConstant.COURSE_ID_DELIMITER));

        return HashUtil.md5(courseIdsStr);
    }
}
