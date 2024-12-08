package com.example.demo.service;

import com.example.demo.modle.GalleryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryCategoryRepository extends JpaRepository<GalleryCategory, Long> {
}
