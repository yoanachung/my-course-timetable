package com.example.enrollment.domain.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public enum Weekday {

    MONDAY      (DayOfWeek.MONDAY,      "월요일"),
    TUESDAY     (DayOfWeek.TUESDAY,     "화요일"),
    WEDNESDAY   (DayOfWeek.WEDNESDAY,   "수요일"),
    THURSDAY    (DayOfWeek.THURSDAY,    "목요일"),
    FRIDAY      (DayOfWeek.FRIDAY,      "금요일");

    private final DayOfWeek dayOfWeek;
    private final String description;
}
