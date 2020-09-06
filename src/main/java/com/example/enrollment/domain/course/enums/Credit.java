package com.example.enrollment.domain.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Credit {

    ONE     (1, "1학점"),
    TWO     (2, "2학점"),
    THREE   (3, "3학점");

    private final int creditValue;
    private final String description;

    public static Credit findByCreditValue(Integer creditValue) {
        return Arrays.stream(values())
                .filter(credit -> credit.creditValue == creditValue)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
