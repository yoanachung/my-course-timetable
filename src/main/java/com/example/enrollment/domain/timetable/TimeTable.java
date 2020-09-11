package com.example.enrollment.domain.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "timeTable", cascade = CascadeType.ALL)
    private List<TimeTableCell> cells = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private TimeTableList timeTableList;

    private TimeTable(List<TimeTableCell> cells) {
        for (TimeTableCell cell : cells) {
            setCell(cell);
        }
    }

    public static TimeTable of(List<TimeTableCell> cells) {
        return new TimeTable(cells);
    }

    void setTimeTableList(TimeTableList timeTableList) {
        this.timeTableList = timeTableList;
    }

    private void setCell(TimeTableCell cell) {
        this.cells.add(cell);
        cell.setTimeTable(this);
    }
}
