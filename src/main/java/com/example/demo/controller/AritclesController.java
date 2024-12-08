        package com.example.demo.controller;

        import com.example.demo.dto.ArticleDto;
        import com.example.demo.modle.Articles;
        import com.example.demo.modle.CategoryOfArticles;
        import com.example.demo.service.AritclesRepository;
        import com.example.demo.service.CategoryOfArticlesRepository;
        import com.example.demo.service.MapStructmapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.Base64;
        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

        @RequestMapping("api/Aritcles")
        @RestController
        @CrossOrigin

        public class AritclesController {

            @Autowired
            private AritclesRepository aritclesRepository;

            @Autowired
           private CategoryOfArticlesRepository categoryOfArticlesRepository;

            @Autowired
            private MapStructmapper mapStructmapper;

            //private static String ARTICLES_DIRECTORY_PATH =System.getProperty("user.dir")+"//PDF//";
            private static String IMAGES_DIRECTORY_PATH =System.getProperty("user.dir")+"//images//";

            //בטוח טוב ועובד
        //    public AritclesController(AritclesRepository aritclesRepository) {
        //        this.aritclesRepository = aritclesRepository;
        //    }

            //נסיון על ידי GPT
                public AritclesController(AritclesRepository aritclesRepository, MapStructmapper mapStructmapper) {
                    this.aritclesRepository = aritclesRepository;
                    this.mapStructmapper = mapStructmapper;
                }


            //Get By Id
            @GetMapping("/getArticlesById/{id}")
            public ResponseEntity<Articles> getArticles(@PathVariable long id) {
                Articles a = aritclesRepository.findById(id).orElse(null);
                if (a== null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(a, HttpStatus.OK);
            }

            //Get All List
            @GetMapping("/getAllArticles")
            public ResponseEntity<List<Articles>> getAllArticles() {
                return new ResponseEntity<>(aritclesRepository.findAll(), HttpStatus.OK);
            }

            //get item from DTO
            // נקודת קצה לשליפת מאמרים עם status=false
        //    @GetMapping("/getPendingArticles")
        //    public ResponseEntity<List<ArticleDto>> getPendingArticles() {
        //        // שליפת כל המאמרים עם status=false
        //        List<Articles> articlesList = aritclesRepository.findByStatusFalse();
        //
        //        // המרת המאמרים ל-DTOs באמצעות MapStruct
        //        List<ArticleDto> articlesDtoList = articlesList.stream()
        //                .map(mapStructmapper::articleToDto)
        //                .collect(Collectors.toList());
        //
        //        // החזרת התוצאה
        //        return ResponseEntity.ok(articlesDtoList);
        //    }

            //שולף לי ומחזיר לי אובייקט של DTO אבל הערך של ID קטגוריה מוחזר כ-NULL
            @GetMapping("/getPendingArticles")
            public ResponseEntity<List<ArticleDto>> getPendingArticles() {
                // שליפת כל המאמרים עם status=false
                List<Articles> articlesList = aritclesRepository.findByStatusFalse();

                // המרת המאמרים ל-DTOs
                List<ArticleDto> articlesDtoList = mapStructmapper.mapArticlesToDtos(articlesList);

                return ResponseEntity.ok(articlesDtoList);
            }


            //נסיון נוסף לGPT
            //שגיאה שלא מחזיר אצ בערך ID של קטגוריה -כלומר מחזיר NULL
//            @GetMapping("/getPendingArticlesWithCategory")
//            public ResponseEntity<List<ArticleDto>> getPendingArticlesWithCategory() {
//                // שליפת כל המאמרים עם status = false
//                List<Articles> articlesList = aritclesRepository.findByStatusFalse();
//
//                // המרת המאמרים ל-DTO כולל categoryId
//                List<ArticleDto> articlesDtoList = articlesList.stream().map(article -> {
//                    ArticleDto dto = mapStructmapper.articleToDto(article);
//                    if (article.getCategoryOfArticles() != null) {
//                        dto.setCategoryId(article.getCategoryOfArticles().getId());
//                    }
//                    return dto;
//                }).collect(Collectors.toList());
//
//                return ResponseEntity.ok(articlesDtoList);
//            }

            @GetMapping("/getPendingArticlesWithCategory")
            public ResponseEntity<List<ArticleDto>> getPendingArticlesWithCategory() {
                // שליפת כל המאמרים עם status = false וcategoryId שאינו null
                List<Articles> articlesList = aritclesRepository.findByStatusFalse();

                // המרת המאמרים ל-DTO כולל categoryId אך רק עבור אלה עם categoryId שאינו null
                List<ArticleDto> articlesDtoList = articlesList.stream()
                        .filter(article -> article.getCategoryOfArticles() != null) // Make sure category is not null
                        .map(article -> {
                            ArticleDto dto = mapStructmapper.articleToDto(article);
                            dto.setCategoryId(article.getCategoryOfArticles().getId());
                            return dto;
                        })
                        .collect(Collectors.toList());

                return ResponseEntity.ok(articlesDtoList);
            }


            //function that return the article with status=false
            @GetMapping("/getListArticlesWithStatusFalse")
            public ResponseEntity<List<Articles>> getListArticlesWithStatusFalse() {
                // קריאה לפונקציה במאגר הנתונים שמחזירה מאמרים עם סטטוס false
                List<Articles> articles = aritclesRepository.findByStatusFalse();
                // החזרת המידע עם סטטוס HTTP 200
                return new ResponseEntity<>(articles, HttpStatus.OK);
            }

            //article's category
            @GetMapping("/getArticlesByCategoryId/{categoryId}")
            public ResponseEntity<List<Articles>> getArticlesByCategoryId(@PathVariable Long categoryId) {
                List<Articles> articles = aritclesRepository.findByCategoryOfArticles_Id(categoryId);
                return new ResponseEntity<>(articles, HttpStatus.OK);
            }


            //Post-add
        //    @PostMapping("/addArticle")
        //    public ResponseEntity<Articles> addArticles(@RequestBody ArticleDto articleDto) {
        //        // Create a new article
        //        Articles articles = new Articles();
        //        articles.setTitle(articleDto.getTitle());
        //        articles.setAuthor(articleDto.getAuthor());
        //        articles.setContent(articleDto.getContent());
        //        articles.setDescription(articleDto.getDescription());
        //        articles.setStatus(false); // always false on create, needs approval
        //
        //        Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
        //        if (category.isPresent())
        //        {
        //            articles.setCategoryOfArticles(category.get()); // Set the existing category as the relationship
        //        }
        //        else
        //        {
        //            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        //        }
        //
        //        Articles newArticles = aritclesRepository.save(articles);
        //        return new ResponseEntity<>(newArticles, HttpStatus.CREATED);
        //    }


            //שגיאה בפונ הזו⬇⬇⬇-----!!!!
            @PostMapping("/addArticle")
            public ResponseEntity<Articles> addArticles(@RequestBody ArticleDto articleDto) {
                // Create a new article
                Articles articles = new Articles();
                articles.setTitle(articleDto.getTitle());
                articles.setAuthor(articleDto.getAuthor());
                articles.setContent(articleDto.getContent());
                articles.setDescription(articleDto.getDescription());
                articles.setStatus(articleDto.isStatus()); // always false on create, needs approval

                Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
                if (category.isPresent())
                {
                    articles.setCategoryOfArticles(category.get()); // Set the existing category as the relationship
                }
                else
                {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }

                Articles newArticles = aritclesRepository.save(articles);
                return new ResponseEntity<>(newArticles, HttpStatus.CREATED);
            }

            //שגיאה בפונ הזו⬇⬇⬇-!!!!!!
//            @PutMapping("/updateArticle/{id}")
//            public ResponseEntity<Articles> updateArticle(@PathVariable long id,@RequestBody ArticleDto articleDto) {
//                // Find the existing article by id
//                Optional<Articles> existingArticle = aritclesRepository.findById(articleDto.getId());
//
//                if (existingArticle.isPresent()) {
//                    Articles articles = existingArticle.get();
//                    // Update the article's values
//                    articles.setTitle(articleDto.getTitle());
//                    articles.setAuthor(articleDto.getAuthor());
//                    articles.setContent(articleDto.getContent());
//                    articles.setDescription(articleDto.getDescription());
//                    articles.setStatus(articleDto.isStatus());
//
//                    Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
//                    if (category.isPresent()) {
//                        articles.setCategoryOfArticles(category.get());
//                    } else {
//                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                    }
//
//                    // Save the updated article
//                    Articles updatedArticles = aritclesRepository.save(articles);
//                    return new ResponseEntity<>(updatedArticles, HttpStatus.OK);
//                } else {
//                    // Article not found
//                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//                }
//            }

            //GPT-נסיון לפתור את הבעיה של הפונקציה!!!!
            @PutMapping("/updateArticle/{id}")
            public ResponseEntity<Articles> updateArticle(@PathVariable long id, @RequestBody ArticleDto articleDto) {
                // Find the existing article by id (using the id from the path variable)
                Optional<Articles> existingArticle = aritclesRepository.findById(id);

                if (existingArticle.isPresent()) {
                    Articles articles = existingArticle.get();
                    // Update the article's values
                    articles.setTitle(articleDto.getTitle());
                    articles.setAuthor(articleDto.getAuthor());
                    articles.setContent(articleDto.getContent());
                    articles.setDescription(articleDto.getDescription());
                    articles.setStatus(articleDto.isStatus());

                    // Find and set the category of the article
                    Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
                    if (category.isPresent()) {
                        articles.setCategoryOfArticles(category.get());
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                    }

                    // Save the updated article
                    Articles updatedArticles = aritclesRepository.save(articles);
                    return new ResponseEntity<>(updatedArticles, HttpStatus.OK);
                } else {
                    // Article not found
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            }


            //cbance from GPT..
            //i add it
            //נועדה להוסיף מאמר חדש
            // למאגר הנתונים תוך וידוא שהוא משויך לקטגוריה קיימת.
        //    @PostMapping("/addArticle")
        //    @Transactional
        //    public ResponseEntity<String> addArticle(@RequestBody Articles articles) {
        //        // בדיקה אם קטגוריה נבחרה
        //        if (articles.getCategoryOfArticles() == null) {
        //            return new ResponseEntity<>("Category is required.", HttpStatus.BAD_REQUEST);
        //        }
        //
        //        CategoryOfArticles category = articles.getCategoryOfArticles();
        //
        //        // בדיקת קיום קטגוריה במאגר
        //        if (!categoryOfArticlesRepository.existsById(category.getId())) {
        //            return new ResponseEntity<>("Category does not exist.", HttpStatus.BAD_REQUEST);
        //        }
        //
        //        // שליפת הקטגוריה הקיימת ממאגר הנתונים
        //        CategoryOfArticles existingCategory = categoryOfArticlesRepository.findById(category.getId()).orElseThrow();
        //        articles.setCategoryOfArticles(existingCategory);
        //
        //        // שמירת המאמר
        //        Articles newArticle = aritclesRepository.save(articles);
        //
        //        // עדכון הקטגוריה עם המאמר החדש
        //        existingCategory.getArticles().add(newArticle);
        //        categoryOfArticlesRepository.save(existingCategory);
        //
        //        return new ResponseEntity<>("Article created successfully.", HttpStatus.CREATED);
        //    }

            //i add it
            //cheak it if we need it or not nmaby we can stay with the simple function
            //דואג לעדכן גם את הקטגוריה בעת הוספת מאמר
        //    @PostMapping("/addArticle")
        //   //   @Transactional
        //    public ResponseEntity<Articles> addArticle(@RequestBody Articles articles) {
        //        // שמירת המאמר
        //        Articles newArticle = aritclesRepository.save(articles);
        //
        //        // עדכון הקטגוריה אם קיימת
        //        CategoryOfArticles category = articles.getCategoryOfArticles();
        //        if (category != null) {
        //            category.getArticles().add(newArticle);
        //            categoryOfArticlesRepository.save(category);
        //        }
        //
        //        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
        //    }

            ////-file/-------------------------------------------------------
        //    @PostMapping("/addArticleWithImage")
        //    public ResponseEntity<Articles> uploadAuthorImage(@RequestPart("fileArticle") Articles articles,
        //                                                 @RequestPart("file") MultipartFile imageFile) throws IOException {
        //        //הניתוב במלא של התמונה +הסימות
        //        Path pathFile = Paths.get(IMAGES_DIRECTORY_PATH + imageFile.getOriginalFilename());
        //        //שמירת התמונה בנתיב
        //        Files.write(pathFile, imageFile.getBytes());
        //        //מעדכנת את הניץוב של התמונה בDATA
        //        articles.setImg_author_of_article(pathFile.toString());
        //
        //        Articles newArticles = aritclesRepository.save(articles);
        //
        //        return new ResponseEntity<>(newArticles, HttpStatus.CREATED);
        //    }


            //Put
            @PutMapping("/updateArticles/{id}")
            public ResponseEntity<Articles> updateArticles(@PathVariable long id, @RequestBody Articles articles) {
                if(id != articles.getId()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                aritclesRepository.save(articles);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            //Deletet
            @DeleteMapping("/deleteArticles/{id}")
            public ResponseEntity<Articles> deleteArticles(@PathVariable long id) {
                aritclesRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


        }
