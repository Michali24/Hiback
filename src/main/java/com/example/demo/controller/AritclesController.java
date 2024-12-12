        package com.example.demo.controller;

        import com.example.demo.dto.ArticleDto;
        import com.example.demo.dto.MeetupSchedleDTO;
        import com.example.demo.modle.Articles;
        import com.example.demo.modle.CategoryOfArticles;
        import com.example.demo.modle.FileMeetup;
        import com.example.demo.modle.MeetapimSchedule;
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
        //i add it
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import java.util.*;
        import java.util.stream.Collectors;

      //  import static com.example.demo.controller.MeetapimScheduleController.File_MeetupSchedule;

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

            private static String PDF_DIRECTORY_PATH =System.getProperty("user.dir")+"//PDF//";
            //ככה GPT הביא לי את הסינטאקס לרשום...
            //private static final String UPLOAD_DIR = "C:/path/to/uploads"; // תיקיית העלאה בשרת

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
//            @GetMapping("/getPendingArticles")
//            public ResponseEntity<List<ArticleDto>> getPendingArticles() {
//                // שליפת כל המאמרים עם status=false
//                List<Articles> articlesList = aritclesRepository.findByStatusFalse();
//
//                // המרת המאמרים ל-DTOs
//                List<ArticleDto> articlesDtoList = mapStructmapper.mapArticlesToDtos(articlesList);
//
//                return ResponseEntity.ok(articlesDtoList);
//            }


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

//            @GetMapping("/getPendingArticlesWithCategory")
//            public ResponseEntity<List<ArticleDto>> getPendingArticlesWithCategory() {
//                // שליפת כל המאמרים עם status = false וcategoryId שאינו null
//                List<Articles> articlesList = aritclesRepository.findByStatusFalse();
//
//                // המרת המאמרים ל-DTO כולל categoryId אך רק עבור אלה עם categoryId שאינו null
//                List<ArticleDto> articlesDtoList = articlesList.stream()
//                        .filter(article -> article.getCategoryOfArticles() != null) // Make sure category is not null
//                        .map(article -> {
//                            ArticleDto dto = mapStructmapper.articleToDto(article);
//                            dto.setCategoryId(article.getCategoryOfArticles().getId());
//                            return dto;
//                        })
//                        .collect(Collectors.toList());
//
//                return ResponseEntity.ok(articlesDtoList);
//            }


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


            //נסיון לתקן את הפונקציה הוספת מאמר
            //9-12-24
            //עבד!!!!!!!!!!!!!!!!!!!!
            //ב"ה
//            @PostMapping("/addArticle")
//            public ResponseEntity<Articles> addArticles(@RequestPart("articleFile") ArticleDto articleDto,
//                                                        @RequestPart("file") MultipartFile file) {
//                // יצירת אובייקט מאמר חדש
//                Articles article = new Articles();
//                article.setTitle(articleDto.getTitle());
//                article.setAuthor(articleDto.getAuthor());
//                article.setContent(articleDto.getContent());
//                article.setDescription(articleDto.getDescription());
//                article.setStatus(articleDto.isStatus()); // בדרך כלל false כשנוצר, צריך אישור
//
//                // חיפוש קטגוריה לפי מזהה
//                Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
//                if (category.isPresent()) {
//                    article.setCategoryOfArticles(category.get()); // קביעת הקטגוריה המתאימה
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // אם לא נמצאה קטגוריה
//                }
//
//                // שמירה של המאמר החדש
//                Articles savedArticle = aritclesRepository.save(article);
//                return new ResponseEntity<>(savedArticle, HttpStatus.CREATED); // החזרת המאמר שנשמר
//            }

            //נסיון בהוספת המאמר עם קובץ PDF
//            @PostMapping("/uploadArticlePDF")
//            public ResponseEntity<Articles> uploadArticlePDF(@RequestPart("articleDto") ArticleDto articleDto,
//                                                           @RequestPart("file") MultipartFile file) {
//                // אם הקובץ ריק
//                if (file.isEmpty()) {
//                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                }
//
//                try {
//                    // יצירת תיקיית ה-upload אם לא קיימת
//                    Path uploadPath = Paths.get(PDF_DIRECTORY_PATH);
//                    if (!Files.exists(uploadPath)) {
//                        Files.createDirectories(uploadPath);
//                    }
//
//                    // שמירה של הקובץ בשרת
//                    String fileName = UUID.randomUUID().toString() + ".pdf"; // שם קובץ ייחודי
//                    Path filePath = uploadPath.resolve(fileName);
//                    file.transferTo(filePath.toFile());
//
//                    // URL של הקובץ כדי להציג אותו ב-React
//                    String fileUrl = "/uploads/" + fileName;
//
//                    // כאן תוכל גם לעשות משהו עם האובייקט ArticleDto
//                    // לדוגמה, לשמור את המאמר ב-DB עם ה-URL של הקובץ
//                    Articles article = new Articles();
//                    article.setTitle(articleDto.getTitle());
//                    article.setAuthor(articleDto.getAuthor());
//                    article.setContent(articleDto.getContent());
//                    article.setDescription(articleDto.getDescription());
//                    article.setStatus(articleDto.isStatus());
//                    article.setPDFArticleFile(articleDto.getPDFArticleFile());  // שמירה של ה-URL של הקובץ ב-DB
//
//                    aritclesRepository.save(article);
//                    // יש להוסיף כאן את הקוד לשמירת המאמר ב-DB (למשל, `articlesRepository.save(article);`)
//
//                    // מחזירים את ה-URL של הקובץ שהועלה
//                    return new ResponseEntity<>(article, HttpStatus.OK);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            }






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
//            @PutMapping("/updateArticle/{id}")
//            public ResponseEntity<Articles> updateArticle(@PathVariable long id, @RequestBody ArticleDto articleDto) {
//                // Find the existing article by id (using the id from the path variable)
//                Optional<Articles> existingArticle = aritclesRepository.findById(id);
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
//                    // Find and set the category of the article
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


            //GPT-09-12-24
            //נסיון...
            //עובד אבל לא שלח באובייקט אצת הID ל המאמר עצמו
//            @PutMapping("/updateArticle/{id}")
//            public ResponseEntity<Articles> updateArticle(@PathVariable long id, @RequestBody ArticleDto articleDto) {
//                // Find the existing article by id (using the id from the path variable)
//                Optional<Articles> existingArticle = aritclesRepository.findById(id);
//
//                if (existingArticle.isPresent()) {
//                    Articles articles = existingArticle.get();
//
//                    // Update the article's values
//                    articles.setTitle(articleDto.getTitle());
//                    articles.setAuthor(articleDto.getAuthor());
//                    articles.setContent(articleDto.getContent());
//                    articles.setDescription(articleDto.getDescription());
//
//                    // Force the status to be true if it's currently false, no matter the status from the DTO
//                    if (!articles.isStatus()) {
//                        articles.setStatus(true);  // Always set to true if status is false
//                    }
//
//                    // Find and set the category of the article
//                    Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
//                    if (category.isPresent()) {
//                        articles.setCategoryOfArticles(category.get());
//                    } else {
//                        // If no category is found, return a BAD_REQUEST status
//                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                    }
//
//                    // Save the updated article
//                    Articles updatedArticles = aritclesRepository.save(articles);
//
//                    // Return the updated article with OK status
//                    return new ResponseEntity<>(updatedArticles, HttpStatus.OK);
//                } else {
//                    // Article not found, return NOT_FOUND status
//                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//                }
//            }

            //נסיון מספר 2-
            //9-12-24-עובדדדדדדדדדדדדדדד
            //ב"ה
            @PutMapping("/updateArticle/{id}")
            public ResponseEntity<Articles> updateArticle(@PathVariable long id, @RequestBody ArticleDto articleDto) {
                // Find the existing article by id (using the id from the path variable)
                Optional<Articles> existingArticle = aritclesRepository.findById(id);

                if (existingArticle.isPresent()) {
                    Articles articles = existingArticle.get();

                    // Even though we receive ID in the DTO, we use the ID from the path variable
                    // We are updating the article based on the ID from the URL, not from the DTO
                    articles.setTitle(articleDto.getTitle());
                    articles.setAuthor(articleDto.getAuthor());
                    articles.setContent(articleDto.getContent());
                    articles.setDescription(articleDto.getDescription());

                    // Force the status to be true if it's currently false
                    if (!articles.isStatus()) {
                        articles.setStatus(true); // Always set to true if status is false
                    }

                    // We don't need to set the article's ID, as it is already set
                    // Find and set the category of the article
                    Optional<CategoryOfArticles> category = categoryOfArticlesRepository.findById(articleDto.getCategoryId());
                    if (category.isPresent()) {
                        articles.setCategoryOfArticles(category.get());
                    } else {
                        // If no category is found, return a BAD_REQUEST status
                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                    }

                    // Save the updated article
                    Articles updatedArticles = aritclesRepository.save(articles);

                    // Return the updated article with OK status
                    return new ResponseEntity<>(updatedArticles, HttpStatus.OK);
                } else {
                    // Article not found, return NOT_FOUND status
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

            //-----------------------------------------------------------------------------------------------------------------
            //add//12-12-24
            //good work pagazzz
            @PostMapping("/addArticle")
            public ResponseEntity<Articles> addArticle(@RequestPart("fileARTICLE") Articles articles,
                                                       @RequestPart("file") MultipartFile imageFile) throws IOException {
                //הניתוב במלא של התמונה +הסימות
                Path pathFile = Paths.get(PDF_DIRECTORY_PATH + imageFile.getOriginalFilename());
                //שמירת התמונה בנתיב
                Files.write(pathFile, imageFile.getBytes());
                //מעדכנת את הניץוב של התמונה בDATA
                articles.setPDFArticleFile(imageFile.getOriginalFilename());

                Articles newArticles = aritclesRepository.save(articles);

                return new ResponseEntity<>(newArticles, HttpStatus.CREATED);
            }

            //get all
            //12-12-24
            @GetMapping("/getListArticles")
            public ResponseEntity<List<ArticleDto>> getListArticles() throws IOException {
                List<Articles> articles = aritclesRepository.findAll();
                List<ArticleDto> articleDtos = new ArrayList<>();
                for (Articles article : articles) {
                    ArticleDto articleDto = MapArticle(article);
                    articleDtos.add(articleDto);
                }
                return new ResponseEntity<>(articleDtos, HttpStatus.OK);
            }

//
//            @GetMapping("/getListArticles")
//            public ResponseEntity<List<ArticleDto>> getListArticles() throws IOException {
//                List<Articles> articles = aritclesRepository.findAll();
//                List<ArticleDto> dtos = new ArrayList<>();
//                for (Articles article : articles) {
//
//                    Path path=Paths.get(PDF_DIRECTORY_PATH + article.getPDFArticleFile());
//                    byte[] bytes = Files.readAllBytes(path);
//                    ArticleDto articleDto = new ArticleDto();
//                    articleDto.setTitle(article.getTitle());
//                    articleDto.setAuthor(article.getAuthor());
//                    articleDto.setContent(article.getContent());
//                    articleDto.setDescription(article.getDescription());
//                    articleDto.setId(article.getId());
//                    articleDto.setCategoryId(article.getCategoryOfArticles()!=null?article.getCategoryOfArticles().getId():null);
//                    articleDto.setPDFArticleFile(bytes);
//
//                    dtos.add(articleDto);
//                }
//                return new ResponseEntity<>(dtos, HttpStatus.OK);
//            }

            //12-12-24
            //יש בעיה עם readAllBytes
            //הוא כל פעם נופל עליה ונשבר...
            private ArticleDto MapArticle(Articles articles) throws IOException{

                Path path = Paths.get(PDF_DIRECTORY_PATH + articles.getPDFArticleFile());
                byte[] arr = Files.readAllBytes(path);

                ArticleDto articleDto = new ArticleDto();


                articleDto.setId(articles.getId());
                articleDto.setTitle(articles.getTitle());
                articleDto.setAuthor(articles.getAuthor());
                articleDto.setContent(articles.getContent());
                articleDto.setDescription(articles.getDescription());
                articleDto.setStatus(articles.isStatus());
                articleDto.setCategoryId(articles.getCategoryOfArticles() != null ? articles.getCategoryOfArticles().getId() : null);
                articleDto.setPDFArticleFile(arr);


                return articleDto;

            }


        }
