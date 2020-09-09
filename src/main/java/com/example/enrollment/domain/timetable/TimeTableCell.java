package com.example.enrollment.domain.timetable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TimeTableCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private TimeTable timeTable;

    private TimeTableCell(Long sectionId) {
        this.sectionId = sectionId;
    }

    public static TimeTableCell of(Long sectionId) {
        return new TimeTableCell(sectionId);
    }

    void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
