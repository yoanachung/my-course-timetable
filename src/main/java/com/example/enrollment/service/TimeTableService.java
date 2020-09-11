package com.example.enrollment.service;

import com.example.enrollment.domain.course.Course;
import com.example.enrollment.domain.course.repository.CourseRepository;
import com.example.enrollment.domain.service.TimeTableMakeService;
import com.example.enrollment.domain.timetable.TimeTableList;
import com.example.enrollment.domain.timetable.repository.TimeTableListRepository;
import com.example.enrollment.dto.TimeTableListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TimeTableService {

    private final CourseRepository courseRepository;
    private final TimeTableListRepository timeTableListRepository;
    private final TimeTableMakeService timeTableMakeService;

    public TimeTableListResponse list(String hash) {
        TimeTableList timeTableList = timeTableListRepository.findByHash(hash);
        List<Course> courses = courseRepository.findAllById(timeTableList.getCourseIds());

        return TimeTableListResponse.of(timeTableList, courses);
    }

    @Transactional
    public TimeTableList makeTimeTable(List<Long> courseIds) {
        List<Course> courses = courseRepository.findAllById(courseIds);

        if (courses.size() != courseIds.size()) {
            throw new IllegalArgumentException("요청 강의 존재하지 않음");
        }

        TimeTableList timeTableList = timeTableMakeService.make(courses);
        timeTableListRepository.save(timeTableList);

        return timeTableList;
    }
}
