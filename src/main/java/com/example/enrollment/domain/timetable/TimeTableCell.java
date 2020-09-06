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
}
