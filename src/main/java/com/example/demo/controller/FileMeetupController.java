        package com.example.demo.controller;

        import com.example.demo.dto.ArticleDto;
        import com.example.demo.dto.FileMeetupDTO;
        import com.example.demo.modle.Articles;
        import com.example.demo.modle.CategoryOfArticles;
        import com.example.demo.modle.FileMeetup;
        //import com.example.demo.modle.MeetingFileType;
        import com.example.demo.modle.GalleryCategory;
        import com.example.demo.service.FileMeetupRepository;
        import com.example.demo.service.GalleryCategoryRepository;
        import org.springframework.beans.factory.annotation.Autowired;
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
        import java.util.Optional;


        @RequestMapping("api/FileMeetup")
        @RestController
        @CrossOrigin
        public class FileMeetupController {

            @Autowired
            private FileMeetupRepository fileMeetupRepository;

            @Autowired
            private GalleryCategoryRepository galleryCategoryRepository;


            //----משתנה בשביל התמונות
            //מחזיר ניתוב של הפרוייקט הנוחכי שלי=user.dir
            private static String File_Meetup =System.getProperty("user.dir")+"//imgesMeetup//";

            public FileMeetupController(FileMeetupRepository fileMeetupRepository) {
                this.fileMeetupRepository = fileMeetupRepository;
            }

            //Get By Id
            @GetMapping("/getFileMeetupById/{id}")
            public ResponseEntity<FileMeetup> getFileMeetup(@PathVariable long id) {
                FileMeetup f= fileMeetupRepository.findById(id).orElse(null);
                if (f== null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(f, HttpStatus.OK);
            }

            //Get All List-עובד מעולה
            @GetMapping("/getAllFileMeetup")
            public ResponseEntity<List<FileMeetup>> getAllFileMeetup() {
                return new ResponseEntity<>(fileMeetupRepository.findAll(), HttpStatus.OK);
            }

            //Get All List-לא עודב הכי טוב
            //שולף לי את התמונה עם נתיב מלאא
            @GetMapping("/getAllMeetupFile")
            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile() {
                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();

                //יצירת רשימה חדשה של FileMeetupDTO
                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();

                //הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
                for (FileMeetup fileMeetup : fileMeetups) {
                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
                    fileMeetupDTO.setId(fileMeetup.getId());
                    fileMeetupDTO.setName(fileMeetup.getName());
                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());


                    //אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא
                    if (fileMeetup.getUrl_file() != null) {
                        fileMeetupDTO.setUrl_file("http://localhost:8080/imgesMeetup/" + fileMeetup.getUrl_file());
                    }

                    fileMeetupDTOs.add(fileMeetupDTO);
                }

                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
            }


            //Post-add
            @PostMapping("/addFileMeetup")
            public ResponseEntity<FileMeetup> addFileMeetup(@RequestBody FileMeetup galleryCategory) {
                FileMeetup newFileMeetup = fileMeetupRepository.save(galleryCategory);
                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }

            @PostMapping("/postFileMeetup")
            public ResponseEntity<FileMeetup> postFileMeetup(@RequestBody FileMeetupDTO fileMeetupDTO) {
                // Create a new article
                FileMeetup fileMeetup = new FileMeetup();
                fileMeetup.setName(fileMeetupDTO.getName());
                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
                fileMeetup.setUrl_file(fileMeetupDTO.getUrl_file());


                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGallery_category_id());
                if (category.isPresent())
                {
                    fileMeetup.setGallery_category(category.get()); // Set the existing category as the relationship
                }
                else
                {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }

                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }


            ////-file/-------------------------------------------------------
                @PostMapping("/addMeetupFile")
                public ResponseEntity<FileMeetup> addMeetupFile(@RequestPart("fileFileMeetup") FileMeetup fileMeetup,
                                                             @RequestPart("file") MultipartFile imageFile) throws IOException {
                    //הניתוב במלא של התמונה +הסימות
                    Path pathFile = Paths.get(File_Meetup + imageFile.getOriginalFilename());
                    //שמירת התמונה בנתיב
                    Files.write(pathFile, imageFile.getBytes());
                    //מעדכנת את הניץוב של התמונה בDATA
                    fileMeetup.setUrl_file(imageFile.getOriginalFilename());

                    FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);

                    return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
                }


            ///נסיון לששלב את 2 הפונקציות ביחד גם הוספה של DTO וגם הוספת תמונה רגילה...



            //Put
            @PutMapping("/updateFileMeetup/{id}")
            public ResponseEntity<FileMeetup> updateFileMeetup(@PathVariable long id, @RequestBody FileMeetup fileMeetup) {
                if(id != fileMeetup.getId()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                fileMeetupRepository.save(fileMeetup);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            //Deletet
            @DeleteMapping("/deleteFileMeetup/{id}")
            public ResponseEntity<FileMeetup> deleteGalleryCategory(@PathVariable long id) {
                fileMeetupRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //------------------------------------------------------------

        //    @PostMapping("/")
        //    public ResponseEntity<FileMeetup> uploadFileMeetup(@RequestBody ("fileMeeyup") FileMeetup fileMeetup, @RequestParam("file") MultipartFile file) {
        //
        //        Path pathFile= Paths.get(DIRECTORY_PATH+file.getOriginalFilename());
        //        Files.write(pathFile,file.getBytes());
        //
        //
        //
        //    }

        }
