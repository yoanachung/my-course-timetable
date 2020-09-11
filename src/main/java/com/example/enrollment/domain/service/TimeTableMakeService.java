package com.example.enrollment.domain.service;

import com.example.enrollment.domain.course.Course;
import com.example.enrollment.domain.course.Section;
import com.example.enrollment.domain.course.enums.Credit;
import com.example.enrollment.domain.timetable.TimeTable;
import com.example.enrollment.domain.timetable.TimeTableCell;
import com.example.enrollment.domain.timetable.TimeTableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimeTableMakeService {
    private static final int MIN_CREDIT_SUM = 18;
    private static final int MAX_CREDIT_SUM = 21;

    public TimeTableList make(List<Course> courses) {
        verifyCanCreateTimeTable(courses);
        TimeTableList timeTableList = createTimeTableList(courses);

        Course firstCourse = courses.get(0);
        List<Section> firstCourseSections = firstCourse.getSections();

        // 첫번째 코스의 i번째 섹션을 선택하는 경우의 섹션 조합 검사
        for (Section firstCourseSection : firstCourseSections) {
            Stack<Section> selectedSections = new Stack<>();
            selectedSections.push(firstCourseSection);
            selectSection(timeTableList, selectedSections, courses, 1);
        }

        return timeTableList;
    }

    private void verifyCanCreateTimeTable(List<Course> courses) {
        int creditSum = courses.stream()
                .map(Course::getCredit)
                .mapToInt(Credit::getCreditValue)
                .sum();

        if (creditSum < MIN_CREDIT_SUM || creditSum > MAX_CREDIT_SUM) {
            throw new RuntimeException("학점의 합이 유효하지 않음:" + creditSum);
        }
    }

    private void selectSection(TimeTableList timeTableList, Stack<Section> selectedSections, List<Course> courses, int courseIndex) {
        Course course = courses.get(courseIndex);
        List<Section> sections = course.getSections();

        for (Section section : sections) {
            // 섹션 선택 불가능
            if (isTimeOverlapped(selectedSections, section)) {
                continue;
            }

            // 섹션 선택 가능
            selectedSections.push(section);

            // 더 선택할 코스가 있음
            if (courseIndex < courses.size() - 1) {
                selectSection(timeTableList, selectedSections, courses, courseIndex + 1);
                continue;
            }

            // 마지막 코스까지 섹션 선택 완료하여 시간표 생성
            timeTableList.addTimeTable(makeTimeTable(selectedSections));
            selectedSections.pop();
        }
    }

    private boolean isTimeOverlapped(Stack<Section> selectedSections, Section section) {
        return selectedSections.stream()
                .anyMatch(selectedSection -> selectedSection.isTimeOverlapped(section));
    }

    private TimeTable makeTimeTable(Stack<Section> selectedSections) {
        return selectedSections.stream()
                .map(section -> TimeTableCell.of(section.getCourse().getId(), section.getId()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), TimeTable::of));
    }

    private TimeTableList createTimeTableList(List<Course> courses) {
        return courses.stream()
                .map(Course::getId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), TimeTableList::new));
    }
}
