package com.example.demo.service;


import com.example.demo.modle.JoiningMeetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoiningMeetupRepository extends JpaRepository<JoiningMeetup,Long> {
    List<JoiningMeetup> findByMeetapimSchedule_Id(Long categoryId);
}
