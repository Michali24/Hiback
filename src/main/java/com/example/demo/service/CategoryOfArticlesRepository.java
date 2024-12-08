package com.example.demo.service;

import com.example.demo.modle.CategoryOfArticles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryOfArticlesRepository extends JpaRepository<CategoryOfArticles, Long> {

    // חיפוש קטגוריה לפי שם
    CategoryOfArticles findByCategoryName(String categoryName);
    // פונקציה שמחפשת קטגוריה לפי ID
//    CategoryOfArticles findByName(String name);

}
