    package com.example.demo.controller;

    import com.example.demo.modle.CategoryOfArticles;
    import com.example.demo.service.CategoryOfArticlesRepository;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    @RequestMapping("api/CategoryOfArticles")
    @RestController
    @CrossOrigin
    public class CategoryOfArticlesController {

        private CategoryOfArticlesRepository categoryOfArticlesRepository;

        public CategoryOfArticlesController(CategoryOfArticlesRepository categoryOfArticlesRepository) {
            this.categoryOfArticlesRepository = categoryOfArticlesRepository;
        }

        //Get By Id
        @GetMapping("/getCategoryOfArticlesById/{id}")
        public ResponseEntity<CategoryOfArticles> getCategoryOfArticles(@PathVariable long id) {
            CategoryOfArticles c = categoryOfArticlesRepository.findById(id).orElse(null);
            if (c == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(c, HttpStatus.OK);
        }

        //Get All List
        @GetMapping("/getAllCategoryOfArticles")
        public ResponseEntity<List<CategoryOfArticles>> getAllCategoryOfArticles() {
            return new ResponseEntity<>(categoryOfArticlesRepository.findAll(), HttpStatus.OK);
        }

        //חיפוש קטוגריה על פי שם והחזרת ID
        // פונקציה להחזרת ID לפי שם קטגוריה
        @GetMapping("/getCategoryIdByName/{name}")
        public ResponseEntity<Long> getCategoryIdByName(@PathVariable String name) {
            CategoryOfArticles category = categoryOfArticlesRepository.findByCategoryName(name);
            if (category != null) {
                return ResponseEntity.ok(category.getId()); // החזרת ה-ID של הקטגוריה
            } else {
                return ResponseEntity.notFound().build(); // אם הקטגוריה לא נמצאה
            }
        }

        //Post-add
        @PostMapping("/addCategoryOfArticles")
        public ResponseEntity<CategoryOfArticles> addCategoryOfArticles(@RequestBody CategoryOfArticles categoryOfArticles) {
            CategoryOfArticles newCategoryOfArticles = categoryOfArticlesRepository.save(categoryOfArticles);
            return new ResponseEntity<>(newCategoryOfArticles, HttpStatus.CREATED);
        }

        //Put
        @PutMapping("/updateCategoryOfArticles/{id}")
        public ResponseEntity<CategoryOfArticles> updateCategoryOfArticles(@PathVariable long id, @RequestBody CategoryOfArticles categoryOfArticles) {
            if(id != categoryOfArticles.getId()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            categoryOfArticlesRepository.save(categoryOfArticles);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        //Deletet
        @DeleteMapping("/deleteCategoryOfArticles/{id}")
        public ResponseEntity<CategoryOfArticles> deleteCategoryOfArticles(@PathVariable long id) {
            categoryOfArticlesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }



    }
