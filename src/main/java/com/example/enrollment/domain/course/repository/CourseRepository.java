package com.example.enrollment.domain.course.repository;

import com.example.enrollment.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
