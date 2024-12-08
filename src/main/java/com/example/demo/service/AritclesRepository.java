package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.modle.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AritclesRepository extends JpaRepository<Articles,Long> {
    List<Articles> findByCategoryOfArticles_Id(Long categoryId);
    // פונקציה למציאת מאמרים עם status = false
    List<Articles> findByStatusFalse();


    ////////////////
    // מתודה להמרה ל-DTO
   // default List<ArticleDto> getArticlesByStatus(boolean status, MapStructmapper mapStructmapper) {
        // שליפת מאמרים לפי סטטוס
 //       List<Articles> articlesList = findByStatusFalse();

        // מיפוי ישויות ל-DTOs
    //    return mapStructmapper.mapArticle(articlesList);
    //}


}
