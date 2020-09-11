package com.example.enrollment.domain.timetable.repository;

import com.example.enrollment.domain.timetable.TimeTableList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableListRepository extends JpaRepository<TimeTableList, Long> {

    Optional<TimeTableList> findByHash(String hash);
}
