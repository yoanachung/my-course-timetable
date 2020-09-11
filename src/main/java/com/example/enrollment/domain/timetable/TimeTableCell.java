package com.example.enrollment.domain.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TimeTableCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long courseId;

    @Column(nullable = false, updatable = false)
    private Long sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private TimeTable timeTable;

    private TimeTableCell(Long courseId, Long sectionId) {
        this.courseId = courseId;
        this.sectionId = sectionId;
    }

    public static TimeTableCell of(Long courseId, Long sectionId) {
        return new TimeTableCell(courseId, sectionId);
    }

    void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
