package com.example.demo.service;

import com.example.demo.modle.Articles;
import com.example.demo.modle.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findByName(String name);
}
