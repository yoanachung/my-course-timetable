package com.example.enrollment.dto;

import com.example.enrollment.domain.course.Course;
import com.example.enrollment.domain.course.Section;
import com.example.enrollment.domain.course.SectionTime;
import com.example.enrollment.domain.course.enums.Period;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class CourseListResponse {
    private Long id;
    private String title;
    private String credit;
    private List<SectionResponse> sections;

    public static CourseListResponse of(Course course) {
        List<SectionResponse> sectionResponses = course.getSections().stream()
                .map(SectionResponse::of)
                .collect(Collectors.toList());

        return CourseListResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .credit(course.getCredit().getDescription())
                .sections(sectionResponses)
                .build();
    }

    @Builder
    @Getter
    public static class SectionResponse {
        private Long id;
        private List<SectionTimeResponse> sectionTimes;

        private static SectionResponse of(Section section) {
            List<SectionTimeResponse> sectionTimeResponses = section.getSectionTimes().stream()
                    .map(SectionTimeResponse::of)
                    .collect(Collectors.toList());

            return SectionResponse.builder()
                    .id(section.getId())
                    .sectionTimes(sectionTimeResponses)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class SectionTimeResponse {
        private String weekday;
        private String period;

        private static SectionTimeResponse of(SectionTime sectionTime) {
            String periodDescription = Period.getPeriodsByFirstPeriod(sectionTime.getPeriod(), sectionTime.getPeriodCount()).stream()
                    .map(Period::getDescription)
                    .collect(Collectors.joining("/"));

            return SectionTimeResponse.builder()
                    .weekday(sectionTime.getWeekday().getDescription())
                    .period(periodDescription)
                    .build();
        }
    }
}
