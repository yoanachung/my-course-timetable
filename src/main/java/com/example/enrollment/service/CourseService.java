package com.example.enrollment.service;

import com.example.enrollment.dto.CourseListResponse;
import com.example.enrollment.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseListResponse> find(Pageable pageable) {
        return courseRepository.findAll(pageable).stream()
                .map(CourseListResponse::of)
                .collect(Collectors.toList());
    }

}
