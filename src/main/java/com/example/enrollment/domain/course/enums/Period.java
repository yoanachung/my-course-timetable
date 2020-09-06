package com.example.enrollment.domain.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Period {

    ONE     (1, "1교시"),
    TWO     (2, "2교시"),
    THREE   (3, "3교시"),
    FOUR    (4, "4교시"),
    FIVE    (5, "5교시"),
    SIX     (6, "6교시"),
    SEVEN   (7, "7교시"),
    EIGHT   (8, "8교시");

    private final int periodValue;
    private final String description;

    public static List<Period> getPeriodsByFirstPeriod(Period firstPeriod, int periodCount) {
        return Arrays.stream(values())
                .filter(period -> period.periodValue >= firstPeriod.periodValue)
                .sorted(Comparator.comparingInt(v -> v.periodValue))
                .limit(periodCount)
                .collect(Collectors.toList());
    }
}
