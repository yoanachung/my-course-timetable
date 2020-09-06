package com.example.enrollment.web;

import com.example.enrollment.dto.CourseListResponse;
import com.example.enrollment.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public String list(Model model,
                       @PageableDefault(value = 100, sort = {"credit"}, direction = Sort.Direction.DESC) Pageable pageable) {

        List<CourseListResponse> courses = courseService.find(pageable);
        model.addAttribute("courses", courses);

        return "/courses/list";
    }
}
