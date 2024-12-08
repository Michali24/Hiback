package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.modle.Articles;
import com.example.demo.modle.CategoryOfArticles;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructmapper {
    //List<ArticleDto> mapArticle(List<Articles> articlesList);
    // מיפוי ישות בודדת ל-DTO
    ArticleDto articleToDto(Articles articles);

    // מיפוי רשימה
    List<ArticleDto> mapArticlesToDtos(List<Articles> articles);

//      default ArticleDto articleToDto(Articles articles) throws IOException {
//        ArticleDto articleDto = new ArticleDto();
//        articleDto.setId(articles.getId());
//        articleDto.setAuthor(articles.getAuthor());
//        articleDto.setContent(articles.getContent());
//        articleDto.setDescription(articles.getDescription());
//        articleDto.setTitle(articles.getTitle());
//        Path path = Paths.get(articles.getImg_author_of_article());
//        byte [] arr = Files.readAllBytes(path);
//        if (arr != null) {
//            articleDto.setImg_author_of_article(arr);
//        }
//        return articleDto;
//
//    }

    ///?????????????????
//    //קובץ של המאמר או טקסט
//    private String PDF_article_file;






}
