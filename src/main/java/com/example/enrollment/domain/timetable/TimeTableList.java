package com.example.enrollment.domain.timetable;

import com.example.enrollment.domain.ProjectConstant;
import com.example.enrollment.domain.timetable.converter.CourseIdsConverter;
import com.example.enrollment.util.HashUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private List<TimeTable> timeTables = new ArrayList<>();

    public TimeTableList(List<Long> courseIds) {
        this.courseIds = courseIds;
        this.hash = TimeTableHashGenerator.generate(courseIds);
    }

    public void addTimeTable(TimeTable timeTable) {
        this.timeTables.add(timeTable);
        timeTable.setTimeTableList(this);
    }
}
