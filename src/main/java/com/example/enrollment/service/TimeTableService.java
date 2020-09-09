package com.example.enrollment.service;

import com.example.enrollment.domain.course.Course;
import com.example.enrollment.domain.course.Section;
import com.example.enrollment.domain.course.repository.CourseRepository;
import com.example.enrollment.domain.timetable.TimeTable;
import com.example.enrollment.domain.timetable.TimeTableCell;
import com.example.enrollment.domain.timetable.TimeTableList;
import com.example.enrollment.domain.timetable.repository.TimeTableListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TimeTableService {

    private final CourseRepository courseRepository;
    private final TimeTableListRepository timeTableListRepository;

    @Transactional
    public void makeTimeTable(List<Long> courseIds) {
        List<Course> courses = courseRepository.findAllById(courseIds);

        Course firstCourse = courses.get(0);
        List<Section> firstCourseSections = firstCourse.getSections();
        TimeTableList timeTableList = new TimeTableList(courseIds);

        // 첫번째 코스의 i번째 섹션을 선택하는 경우의 섹션 조합 검사
        for (Section firstCourseSection : firstCourseSections) {
            Stack<Section> selectedSections = new Stack<>();
            selectedSections.push(firstCourseSection);
            selectSection(timeTableList, selectedSections, courses, 1);
        }

        timeTableListRepository.save(timeTableList);
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
                .map(section -> TimeTableCell.of(section.getId()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), TimeTable::of));
    }
}
