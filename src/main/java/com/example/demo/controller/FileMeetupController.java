        package com.example.demo.controller;


        import com.example.demo.dto.FileMeetupDTO;
        import com.example.demo.modle.FileMeetup;
        //import com.example.demo.modle.MeetingFileType;
        import com.example.demo.modle.GalleryCategory;
        import com.example.demo.service.FileMeetupRepository;
        import com.example.demo.service.GalleryCategoryRepository;
        import com.example.demo.service.MapStructmapper;
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

            //Get All List-עובד מעולה
            @GetMapping("/getFileMeetup")
            public ResponseEntity<List<FileMeetup>> getFileMeetup() {
                return new ResponseEntity<>(fileMeetupRepository.findAll(), HttpStatus.OK);
            }

            @GetMapping("/getFileMeetup/{id}")
            public ResponseEntity<FileMeetupDTO> getFileMeetupById(@PathVariable Long id) {
                try
                {
                    // חיפוש המודעה לפי מזהה
                    FileMeetup fileMeetup = fileMeetupRepository.findById(id).orElse(null);

                    // אם לא נמצא אובייקט, מחזירים NOT_FOUND
                    if (fileMeetup == null) {
                        // הוספת לוג כדי לוודא אם ה-`id` לא נמצא
                        System.out.println("FileMeetup not found with ID: " + id);
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                    //קריאה לפונ
                    FileMeetupDTO fileMeetupDTO = MapFileMeetupDTO(fileMeetup);

                    // החזרת ה-DTO עם כל הנתונים
                    return new ResponseEntity<>(fileMeetupDTO, HttpStatus.OK);
                }
                catch (IOException e)
                {
                    // טיפול בחריגות IO (למשל אם אין גישה לקובץ)
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


            @PostMapping("/addMeetupFile2")
            public ResponseEntity<FileMeetup> addMeetupFile2(@RequestPart("fileFileMeetup") FileMeetupDTO fileMeetupDTO,
                                                             @RequestPart("file") MultipartFile file) throws IOException {

                FileMeetup fileMeetup = new FileMeetup();
                fileMeetup.setName(fileMeetupDTO.getName());
                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());

                // ניהול הקטגוריה
                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGalleryCategoryId());
                if (category.isPresent()) {
                    fileMeetup.setGalleryCategory(category.get());
                } else {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }

                // טיפול בקבצים לפי סוג
                if (fileMeetupDTO.getTypeFile().equals("image")) {
                    // אם זה תמונה, שמירה בשרת כקובץ תמונה
                    Path pathFile = Paths.get(File_Meetup + file.getOriginalFilename());
                    Files.write(pathFile, file.getBytes());

                    // עדכון נתיב התמונה במסד הנתונים
                    fileMeetup.setUrl_file(file.getOriginalFilename());

                    System.out.println("Image file URL: " + fileMeetup.getUrl_file());

                } else if (fileMeetupDTO.getTypeFile().equals("video")) {
                    // אם זה סרטון, שמירת ה-URL של הסרטון (לא שומרים את הקובץ עצמו)
                    fileMeetup.setUrl_file(fileMeetupDTO.getUrl_file());
                }

                // שמירת האובייקט במסד הנתונים
                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);

                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }

            @GetMapping("/getAllMeetupFile1")
            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile1() throws IOException {
                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll(); // מקבלים את כל הרשומות
                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>(); // רשימה לשמירת ה-DTOs

                for (FileMeetup fileMeetup : fileMeetups) {
                    FileMeetupDTO fileMeetupDTO = MapFileMeetupDTO(fileMeetup);
                    fileMeetupDTOs.add(fileMeetupDTO); // הוספת ה-DTO לרשימה
                }
                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK); // מחזירים את כל ה-DTOs
            }

            //Post-add
            //לא צריך
            @PostMapping("/addFileMeetup")
            public ResponseEntity<FileMeetup> addFileMeetup(@RequestBody FileMeetup galleryCategory) {
                FileMeetup newFileMeetup = fileMeetupRepository.save(galleryCategory);
                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }

            //Put
            @PutMapping("/updateFileMeetup/{id}")
            public ResponseEntity<FileMeetup> updateFileMeetup(@PathVariable long id, @RequestBody FileMeetup fileMeetup) {
                if(id != fileMeetup.getId()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                fileMeetupRepository.save(fileMeetup);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            //Delete
            @DeleteMapping("/deleteFileMeetup/{id}")
            public ResponseEntity<FileMeetup> deleteGalleryCategory(@PathVariable long id) {
                fileMeetupRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


            @GetMapping("/getFileMeetupsByGalleryCategoryId/{galleryCategoryId}")
            public ResponseEntity<List<FileMeetupDTO>>getFileMeetupsByGalleryCategoryId(@PathVariable Long galleryCategoryId) throws IOException {
                List<FileMeetup> fileMeetups = fileMeetupRepository.findByGalleryCategory_Id(galleryCategoryId);
                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
                for (FileMeetup fileMeetup : fileMeetups) {
                    FileMeetupDTO fileMeetupDTO =MapFileMeetupDTO(fileMeetup);
                    fileMeetupDTOs.add(fileMeetupDTO);
                }

                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
            }

            private FileMeetupDTO MapFileMeetupDTO(FileMeetup fileMeetup) throws IOException {
                // יצירת אובייקט DTO והעתקת נתונים
                FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
                fileMeetupDTO.setId(fileMeetup.getId());
                fileMeetupDTO.setName(fileMeetup.getName());
                fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
                fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
                fileMeetupDTO.setGalleryCategoryId(fileMeetup.getGalleryCategory() != null ? fileMeetup.getGalleryCategory().getId() : null);

                if (fileMeetup.getTypeFile().equals("image")) {
                    Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());

                    // בדוק אם הקובץ קיים
                    if (Files.exists(path)) {
                        // קריאת המידע מהקובץ והמרת הנתונים למערך ביטים
                        byte[] arr = Files.readAllBytes(path);
                        fileMeetupDTO.setFileData(arr);
                    }
                }

                return fileMeetupDTO;
            }
        }
