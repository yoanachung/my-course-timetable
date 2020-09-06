package com.example.enrollment.domain.timetable.repository;

import com.example.enrollment.domain.timetable.TimeTableList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableListRepository extends JpaRepository<TimeTableList, Long> {

    TimeTableList findByHash(String hash);
}
