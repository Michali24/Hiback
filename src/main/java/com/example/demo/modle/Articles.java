package com.example.demo.modle;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;

@Entity
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    //כדי לאשפר טקסט מאוד גדול
    @Lob
    private String content;
    private String description;

    //האם הוא מוצג (נבדרק ) או לא
    private boolean status;

//    //קובץ של המאמר או טקסט
//    private String PDF_article_file;
    //תמונה של הכותב או מגיש המאמר
//    private String img_author_of_article;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryOfArticles categoryOfArticles;


    //get&&set

    public void setCategoryOfArticles(CategoryOfArticles categoryOfArticles) {
        this.categoryOfArticles = categoryOfArticles;
    }
    public CategoryOfArticles getCategoryOfArticles() {
        return categoryOfArticles;
    }

//    public String getPDF_article_file() {
//        return PDF_article_file;
//    }
//
//    public void setPDF_article_file(String PDF_article_file) {
//        this.PDF_article_file = PDF_article_file;
//    }
//    public String getImg_author_of_article() {
//        return img_author_of_article;
//    }
//
//    public void setImg_author_of_article(String img_author_of_article) {
//        this.img_author_of_article = img_author_of_article;
//    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
