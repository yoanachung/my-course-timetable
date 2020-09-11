package com.example.enrollment.web;

import com.example.enrollment.domain.timetable.TimeTableList;
import com.example.enrollment.dto.TimeTableCreateRequest;
import com.example.enrollment.dto.TimeTableCreateResponse;
import com.example.enrollment.dto.TimeTableListResponse;
import com.example.enrollment.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TimeTableApiController {

    private final TimeTableService timeTableService;

    @PostMapping("/timetables")
    public ResponseEntity<TimeTableCreateResponse> schedule(@RequestBody TimeTableCreateRequest request) {
        request.validate();

        TimeTableList timeTableList = timeTableService.makeTimeTable(request.getCourseIds());

        return ResponseEntity
                .created(URI.create("/timetables/" + timeTableList.getHash()))
                .body(new TimeTableCreateResponse(timeTableList));
    }

    @GetMapping("/timetables/{hash}")
    public ResponseEntity<TimeTableListResponse> list(@PathVariable("hash") String hash) {
        TimeTableListResponse timeTableListResponse = timeTableService.list(hash);

        return ResponseEntity
                .ok(timeTableListResponse);
    }
}
