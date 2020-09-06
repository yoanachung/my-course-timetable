package com.example.enrollment.web;

import com.example.enrollment.dto.TimeTableCreateRequest;
import com.example.enrollment.dto.TimeTableCreateResponse;
import com.example.enrollment.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TimeTableApiController {

    private final ScheduleService scheduleService;

    @PostMapping("/timetable")
    public ResponseEntity<TimeTableCreateResponse> schedule(@RequestBody TimeTableCreateRequest request) {
        return ResponseEntity
                .created(URI.create("/courses"))
                .body(new TimeTableCreateResponse());
    }
}
