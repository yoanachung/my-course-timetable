package com.example.enrollment.dto;

import com.example.enrollment.domain.course.Course;
import com.example.enrollment.domain.course.Section;
import com.example.enrollment.domain.course.SectionTime;
import com.example.enrollment.domain.timetable.TimeTable;
import com.example.enrollment.domain.timetable.TimeTableList;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
@Getter
public class TimeTableListResponse {
    private String hash;
    private List<TimeTableResponse> timeTables;

    public static TimeTableListResponse of(TimeTableList timeTableList, List<Course> courses) {
        Map<Long, Section> sectionById = courses.stream()
                .flatMap(course -> course.getSections().stream())
                .collect(Collectors.toMap(Section::getId, Function.identity()));

        List<TimeTableResponse> timeTables = timeTableList.getTimeTables().stream()
                .map(timeTable -> TimeTableResponse.of(timeTable, sectionById))
                .collect(Collectors.toList());

        return TimeTableListResponse.builder()
                .hash(timeTableList.getHash())
                .timeTables(timeTables)
                .build();
    }

    @Builder
    @Getter
    public static class TimeTableResponse {
        private Long id;
        private List<TimeTableCellResponse> cells;

        public static TimeTableResponse of(TimeTable timeTable, Map<Long, Section> sectionById) {
            List<TimeTableCellResponse> cells = timeTable.getCells().stream()
                    .map(cell -> TimeTableCellResponse.of(sectionById.get(cell.getSectionId())))
                    .collect(Collectors.toList());

            return TimeTableResponse.builder()
                    .id(timeTable.getId())
                    .cells(cells)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class TimeTableCellResponse {
        private Long courseId;
        private Long sectionId;
        private List<TimeTableCellTimeResponse> times;

        public static TimeTableCellResponse of(Section section) {
            List<TimeTableCellTimeResponse> times = section.getSectionTimes().stream()
                    .map(TimeTableCellTimeResponse::of)
                    .collect(Collectors.toList());

            return TimeTableCellResponse.builder()
                    .courseId(section.getCourse().getId())
                    .sectionId(section.getId())
                    .times(times)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class TimeTableCellTimeResponse {
        private int weekday;
        private int period;
        private int periodCount;

        public static TimeTableCellTimeResponse of(SectionTime sectionTime) {
            return TimeTableCellTimeResponse.builder()
                    .weekday(sectionTime.getWeekday().getDayOfWeek().getValue())
                    .period(sectionTime.getPeriod().getPeriodValue())
                    .periodCount(sectionTime.getPeriodCount())
                    .build();
        }
    }
}
