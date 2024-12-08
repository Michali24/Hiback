package com.example.demo.service;


import com.example.demo.modle.MeetapimSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MeetapimScheduleRepository extends JpaRepository<MeetapimSchedule,Long> {
    Optional<MeetapimSchedule> findTopByOrderByIdDesc(); //שליפה לפי ה-ID הגבוה ביותר

}
