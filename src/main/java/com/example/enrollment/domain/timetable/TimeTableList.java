package com.example.enrollment.domain.timetable;

import com.example.enrollment.domain.timetable.converter.CourseIdsConverter;

import javax.persistence.*;
import java.util.List;

@Entity
public class TimeTableList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String hash;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = CourseIdsConverter.class)
    private List<Long> courseIds;

    @OneToMany(mappedBy = "timeTableList", cascade = CascadeType.ALL)
    private List<TimeTable> timeTables;
}
