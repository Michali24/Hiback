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
        import org.springframework.http.HttpHeaders;
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


                public AritclesController(AritclesRepository aritclesRepository, MapStructmapper mapStructmapper) {
                    this.aritclesRepository = aritclesRepository;
                    this.mapStructmapper = mapStructmapper;
                }

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


            @PutMapping("/updateArticle/{id}")
            public ResponseEntity<ArticleDto> updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
                // חיפוש המאמר על פי ה-ID
                Articles articles = aritclesRepository.findById(id).orElse(null);
                if (articles == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                // עדכון רק של הסטטוס
                articles.setStatus(true);

                // שמירת המאמר ב-DB
                aritclesRepository.save(articles);

                // המרת המאמר המעודכן ל-DTO
                ArticleDto updatedArticleDto = new ArticleDto();
                updatedArticleDto.setId(articles.getId());
                updatedArticleDto.setTitle(articles.getTitle());
                updatedArticleDto.setAuthor(articles.getAuthor());
                updatedArticleDto.setDescription(articles.getDescription());
                updatedArticleDto.setStatus(articles.isStatus());
                updatedArticleDto.setCategoryId(articles.getCategoryOfArticles() != null ? articles.getCategoryOfArticles().getId() : null);

                // אם יש קובץ PDF, אפשר לקרוא אותו רק במקרה הצורך
                byte[] arr = null;
                if (articles.getPDFArticleFile() != null) {
                    Path path = Paths.get(PDF_DIRECTORY_PATH + articles.getPDFArticleFile());
                    try {
                        arr = Files.readAllBytes(path);
                    } catch (IOException e) {
                        // טיפול בשגיאה קריאה לקובץ
                        e.printStackTrace();
                    }
                }
                updatedArticleDto.setPDFArticleFile(arr);

                // החזרת ה-DTO המעודכן עם הסטטוס החדש
                return new ResponseEntity<>(updatedArticleDto, HttpStatus.OK);
            }


            @GetMapping("/getListArticlesWithStatusFalse")
            public ResponseEntity<List<ArticleDto>> getListArticlesWithStatusFalse() throws IOException {
                // קריאה לפונקציה במאגר הנתונים שמחזירה מאמרים עם סטטוס false
                List<Articles> articles = aritclesRepository.findByStatusFalse();
                List<ArticleDto> articleDtos = new ArrayList<>();
                for (Articles article : articles) {
                    ArticleDto articleDto = MapArticle(article);
                    articleDtos.add(articleDto);
                }
                return new ResponseEntity<>(articleDtos, HttpStatus.OK);
            }

            @GetMapping("/getArticlesByCategoryId/{categoryId}")
            public ResponseEntity<List<ArticleDto>> getArticlesByCategoryId(@PathVariable Long categoryId)throws IOException {
                List<Articles> articles = aritclesRepository.findByCategoryOfArticles_Id(categoryId);
                List<ArticleDto> articleDtos = new ArrayList<>();
                for (Articles article : articles) {
                    ArticleDto articleDto = MapArticle(article);
                    articleDtos.add(articleDto);
                }
                return new ResponseEntity<>(articleDtos, HttpStatus.OK);
            }

            @GetMapping("/getArticleById/{id}")
            public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id)throws IOException {
                Articles a = aritclesRepository.findById(id).orElse(null);
                if (a== null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ArticleDto articleDto =MapArticle(a);

                return new ResponseEntity<>(articleDto, HttpStatus.OK);
            }



            @DeleteMapping("/deleteArticles/{id}")
            public ResponseEntity<Articles> deleteArticles(@PathVariable long id) {
                aritclesRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }



            private ArticleDto MapArticle(Articles articles) throws IOException{

                Path path = Paths.get(PDF_DIRECTORY_PATH + articles.getPDFArticleFile());
                byte[] arr = Files.readAllBytes(path);

                ArticleDto articleDto = new ArticleDto();

                articleDto.setId(articles.getId());
                articleDto.setTitle(articles.getTitle());
                articleDto.setAuthor(articles.getAuthor());
                articleDto.setDescription(articles.getDescription());
                articleDto.setStatus(articles.isStatus());
                articleDto.setCategoryId(articles.getCategoryOfArticles() != null ? articles.getCategoryOfArticles().getId() : null);
                articleDto.setPDFArticleFile(arr);


                return articleDto;

            }


        }
