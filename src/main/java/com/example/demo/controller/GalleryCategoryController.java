    package com.example.demo.controller;

    import com.example.demo.dto.FileMeetupDTO;
    import com.example.demo.dto.GalleryCategoryDTO;
    import com.example.demo.modle.FileMeetup;
    import com.example.demo.modle.GalleryCategory;
    import com.example.demo.modle.MeetapimSchedule;
    import com.example.demo.service.GalleryCategoryRepository;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.List;


    import com.fasterxml.jackson.databind.ObjectMapper;

    @RequestMapping("api/GalleryCategory")
    @RestController
    @CrossOrigin
    public class GalleryCategoryController {

        private GalleryCategoryRepository galleryCategoryRepository;

        private static String File_GalleryCategoty =System.getProperty("user.dir")+"//imgesMeetup//";


        public GalleryCategoryController(GalleryCategoryRepository galleryCategoryRepository) {
            this.galleryCategoryRepository = galleryCategoryRepository;
        }

        //Get By Id
        @GetMapping("/getGalleryCategorysById/{id}")
        public ResponseEntity<GalleryCategory> getGalleryCategory(@PathVariable long id) {
            GalleryCategory g= galleryCategoryRepository.findById(id).orElse(null);
            if (g== null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(g, HttpStatus.OK);
        }

        //Get All List
        @GetMapping("/getAllGalleryCategory")
        public ResponseEntity<List<GalleryCategory>> getAllGalleryCategory() {
            return new ResponseEntity<>(galleryCategoryRepository.findAll(), HttpStatus.OK);
        }


        @GetMapping("/getAllGalleryCategoryFile")
        public ResponseEntity<List<GalleryCategoryDTO>> getAllGalleryCategoryFile() {
            List<GalleryCategory> galleryCategories = galleryCategoryRepository.findAll();

            //יצירת רשימה חדשה של FileMeetupDTO
            List<GalleryCategoryDTO>  galleryCategoryDTOS= new ArrayList<>();

            //הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
            for (GalleryCategory galleryCategory : galleryCategories) {
                GalleryCategoryDTO galleryCategoryDTO= new GalleryCategoryDTO();
                galleryCategoryDTO.setId(galleryCategory.getId());
                galleryCategoryDTO.setNameMeetup(galleryCategory.getNameMeetup());
                galleryCategoryDTO.setDescriptionMeetup(galleryCategory.getDescriptionMeetup());
                galleryCategoryDTO.setCompanyName(galleryCategory.getCompanyName());


                //אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא
                if (galleryCategory.getImg_meetup() != null) {
                    galleryCategoryDTO.setImg_meetup("http://localhost:8080/imgesMeetup/" + galleryCategory.getImg_meetup());
                }

                galleryCategoryDTOS.add(galleryCategoryDTO);
            }

            return new ResponseEntity<>(galleryCategoryDTOS, HttpStatus.OK);
        }



        //Post-add
        @PostMapping("/addGalleryCategory")
        public ResponseEntity<GalleryCategory> addGalleryCategory(@RequestBody GalleryCategory galleryCategory) {
            GalleryCategory newGalleryCategory = galleryCategoryRepository.save(galleryCategory);
            return new ResponseEntity<>(newGalleryCategory, HttpStatus.CREATED);
        }

        ////-file/------------------------------דרך פוסטמן עובד מעולה!!!
        @PostMapping("/addGalleryCategoryFile")
        public ResponseEntity<GalleryCategory> uploadAuthorImage(@RequestPart("fileGalleryCategory") GalleryCategory galleryCategory,
                                                                  @RequestPart("file") MultipartFile imageFile) throws IOException {
            //הניתוב במלא של התמונה +הסימות
            Path pathFile = Paths.get(File_GalleryCategoty + imageFile.getOriginalFilename());
            //שמירת התמונה בנתיב
            Files.write(pathFile, imageFile.getBytes());
            //מעדכנת את הניץוב של התמונה בDATA
            galleryCategory.setImg_meetup(imageFile.getOriginalFilename());

            GalleryCategory newGalleryCategory = galleryCategoryRepository.save(galleryCategory);

            return new ResponseEntity<>(newGalleryCategory, HttpStatus.CREATED);
        }


        //GPT-POST---------------------------------
//        @PostMapping("/addGalleryCategoryFile")
//        public ResponseEntity<GalleryCategory> uploadAuthorImage(
//                @RequestPart("fileGalleryCategory") String fileGalleryCategoryJSON, // JSON שמתקבל כשדה
//                @RequestPart("file") MultipartFile imageFile // קובץ שמתקבל כשדה
//        ) throws IOException {
//            // הדפסת נתונים ללוג לצורכי דיבוג
//            System.out.println("Received fileGalleryCategory JSON: " + fileGalleryCategoryJSON);
//            System.out.println("Received file name: " + imageFile.getOriginalFilename());
//
//            // המרת ה-JSON ל-GalleryCategory
//            ObjectMapper mapper = new ObjectMapper(); // יצירת ObjectMapper
//            GalleryCategory galleryCategory = mapper.readValue(fileGalleryCategoryJSON, GalleryCategory.class); // המרת JSON לאובייקט
//
//            // שמירת הקובץ בתיקייה
//            Path pathFile = Paths.get(File_GalleryCategoty + imageFile.getOriginalFilename());
//            Files.write(pathFile, imageFile.getBytes()); // כתיבת תוכן הקובץ
//
//            // עדכון הנתיב ושמירת הנתונים בבסיס הנתונים
//            galleryCategory.setImg_meetup(imageFile.getOriginalFilename()); // עדכון נתיב התמונה
//            GalleryCategory newGalleryCategory = galleryCategoryRepository.save(galleryCategory); // שמירת אובייקט
//
//            return new ResponseEntity<>(newGalleryCategory, HttpStatus.CREATED); // החזרת תשובה ללקוח
//        }



        //Put
        @PutMapping("/updateGalleryCategory/{id}")
        public ResponseEntity<GalleryCategory> updateGalleryCategory(@PathVariable long id, @RequestBody GalleryCategory galleryCategory) {
            if(id != galleryCategory.getId()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            galleryCategoryRepository.save(galleryCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        //Deletet
        @DeleteMapping("/deleteGalleryCategory/{id}")
        public ResponseEntity<GalleryCategory> deleteGalleryCategory(@PathVariable long id) {
            galleryCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
