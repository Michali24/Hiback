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

            //Get By Id
//            @GetMapping("/getFileMeetupById/{id}")
//            public ResponseEntity<FileMeetup> getFileMeetup(@PathVariable long id) {
//                FileMeetup f= fileMeetupRepository.findById(id).orElse(null);
//                if (f== null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                return new ResponseEntity<>(f, HttpStatus.OK);
//            }

            //נסיון לשנות שיחזיר אובייקט של DTO
//            @GetMapping("/getFileMeetupById/{id}")
//            public ResponseEntity<FileMeetupDTO> getFileMeetup(@PathVariable long id) {
//                FileMeetup f= fileMeetupRepository.findById(id).orElse(null);
//                FileMeetupDTO dto = new FileMeetupDTO();
//                dto.setId(f.getId());
//                dto.setTypeFile(f.getTypeFile());
//                dto.setUrl_file(f.getUrl_file());
//                dto.setName(f.getName());
//                dto.setGalleryCategory_id(f.getId());
//
//                if (dto== null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                return new ResponseEntity<>(dto, HttpStatus.OK);
//            }

            //GET BY ID
            //10-12-24
            //מחזיא שם תמונה וסיומת
//            @GetMapping("/getFileMeetupById/{id}")
//            public ResponseEntity<FileMeetupDTO> getFileMeetup(@PathVariable long id) {
//                FileMeetup f = fileMeetupRepository.findById(id).orElse(null);
//
//                // אם לא נמצא אובייקט, מחזירים NOT_FOUND
//                if (f == null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//
//                // יצירת ה-DTO והגדרת הערכים
//                FileMeetupDTO dto = new FileMeetupDTO();
//                dto.setId(f.getId());
//                dto.setName(f.getName());
//                dto.setTypeFile(f.getTypeFile());
//                dto.setUrl_file(f.getUrl_file());
//                dto.setGallery_category_id(f.getGallery_category() != null ? f.getGallery_category().getId() : null);
//
//                return new ResponseEntity<>(dto, HttpStatus.OK);
//            }

            //10-12-24
            //נסיום שיחזיר מערך ביטים
//            @GetMapping("/getFileMeetupById/{id}")
//            public ResponseEntity <FileMeetupDTO> getFileMeetupById(@PathVariable long id) throws IOException {
//                FileMeetup fileMeetup = fileMeetupRepository.findById(id).orElse(null);
//
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                if (fileMeetup != null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//                    fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//
//                    // אם ה-URL של התמונה קיים, המרתו למערך ביטים
//                    if (fileMeetup.getUrl_file() != null) {
//                        Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());
//                        byte[] fileData = Files.readAllBytes(path);
//                        fileMeetupDTO.setFileData(fileData);
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//
//
//                return new ResponseEntity<>(fileMeetupDTO, HttpStatus.OK);
//            }

            //נסיון נוסף
            //10-12-24
            //לא עובד
//            @GetMapping("/getFileMeetupById/{id}")
//            public ResponseEntity<FileMeetupDTO> getFileMeetupById(@PathVariable Long id) throws IOException {
//                // חיפוש המודעה לפי מזהה
//                FileMeetup fileMeetup = fileMeetupRepository.findById(id).orElse(null);
//
//                if (fileMeetup == null) {
//                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//                }
//
//                // קריאת קובץ התמונה
//                Path path = Paths.get(fileMeetup.getUrl_file());
//                byte[] arr = Files.readAllBytes(path);
//
//                FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                fileMeetupDTO.setId(fileMeetup.getId());
//                fileMeetupDTO.setName(fileMeetup.getName());
//                fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//                fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
//                fileMeetupDTO.setFileData(arr);
//                fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//
//                return new ResponseEntity<>(fileMeetupDTO, HttpStatus.OK);
//            }
            //Get All List-עובד מעולה
            @GetMapping("/getFileMeetup")
            public ResponseEntity<List<FileMeetup>> getFileMeetup() {
                return new ResponseEntity<>(fileMeetupRepository.findAll(), HttpStatus.OK);
            }

            //שוקי!!!!
            // עובד!!!!!!!!!!!!!!!!!!!
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

            //הקוד ללא הפונ המקוצרת..
//            @GetMapping("/getFileMeetup/{id}")
//            public ResponseEntity<FileMeetupDTO> getFileMeetupById(@PathVariable Long id) {
//                try
//                {
//                    // חיפוש המודעה לפי מזהה
//                    FileMeetup fileMeetup = fileMeetupRepository.findById(id).orElse(null);
//
//                    // אם לא נמצא אובייקט, מחזירים NOT_FOUND
//                    if (fileMeetup == null) {
//                        // הוספת לוג כדי לוודא אם ה-`id` לא נמצא
//                        System.out.println("FileMeetup not found with ID: " + id);
//                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                    }
//
//                    //קריאה לפונ
//                    FileMeetupDTO fileMeetupDTO = MapFileMeetupDTO(fileMeetup);
//
////                    // אם ה-URL של הקובץ קיים, קרא את הקובץ
////                    if (fileMeetup.getUrl_file() != null && !fileMeetup.getUrl_file().isEmpty()) {
////                        Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());
////
////                        // בדוק אם הקובץ קיים
////                        if (Files.exists(path)) {
////                            // קריאת המידע מהקובץ והמרת הנתונים למערך ביטים
////                            byte[] arr = Files.readAllBytes(path);
////                            fileMeetupDTO.setFileData(arr);
////                        }
////                    } else {
////                        // אם אין URL, מחזירים מערך ביטים ריק
////                        fileMeetupDTO.setFileData(new byte[0]);
////                    }
//
//                    // החזרת ה-DTO עם כל הנתונים
//                    return new ResponseEntity<>(fileMeetupDTO, HttpStatus.OK);
//                }
//                catch (IOException e)
//                {
//                    // טיפול בחריגות IO (למשל אם אין גישה לקובץ)
//                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            }

            //Get All List-לא עודב הכי טוב
            //שולף לי את התמונה עם נתיב מלאא
            //שיניתי לנתיב חסר...רק השם של התמונה עם סיומת...
//            @GetMapping("/getAllMeetupFile")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile() {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                //יצירת רשימה חדשה של FileMeetupDTO
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                //הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
//                for (FileMeetup fileMeetup : fileMeetups) {
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//
//
//                    //אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא
//                    if (fileMeetup.getUrl_file() != null) {
//                        fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }
//
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
//            }

            //נסיון מGPT
            //עובדדדדדדדד
            //ב"ה
//            @GetMapping("/getAllMeetupFile")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile() {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                // יצירת רשימה חדשה של FileMeetupDTO
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                // הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
//                for (FileMeetup fileMeetup : fileMeetups) {
//
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//
//                    // אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא
//                    if (fileMeetup.getUrl_file() != null) {
//                        fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
//                    }
//
//                    // הוספת ID של הקטגוריה אם קיים
//                    if (fileMeetup.getGallery_category() != null) {
//                        fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }
//
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
//            }

            //10-12-24
            //GOST
            //עובד ב"ה!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            @PostMapping("/addMeetupFile1")
//            public ResponseEntity<FileMeetup> addMeetupFile1(@RequestPart("fileFileMeetup") FileMeetupDTO fileMeetupDTO,
//                                                            @RequestPart("file") MultipartFile imageFile) throws IOException {
//
//                FileMeetup fileMeetup = new FileMeetup();
//                fileMeetup.setName(fileMeetupDTO.getName());
//                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
//
//                // ניהול הקטגוריה
//                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGallery_category_id());
//                if (category.isPresent()) {
//                    fileMeetup.setGallery_category(category.get());
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//
//                // שמירת הקובץ בנתיב המלא
//                Path pathFile = Paths.get(File_Meetup + imageFile.getOriginalFilename());
//                Files.write(pathFile, imageFile.getBytes());
//
//                // עדכון נתיב התמונה במסד הנתונים
//                fileMeetup.setUrl_file(imageFile.getOriginalFilename());
//
//                // שמירת האובייקט במסד הנתונים
//                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
//
//                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
//            }

            //נסיון לשנות את הפונ POST שעובדת שהיא תבדוק אם נכנס וידיו או תמונה ולפי זה תדע איך לשמור את הנתונים
            //GPT
            //ב"ה עובד!!!!!!!!!!!!!!!!!!!!!
            //11-12-24
//            @PostMapping("/addMeetupFile2")
//            public ResponseEntity<FileMeetup> addMeetupFile2(@RequestPart("fileFileMeetup") FileMeetupDTO fileMeetupDTO,
//                                                             @RequestPart("file") MultipartFile file) throws IOException {
//
//                FileMeetup fileMeetup = new FileMeetup();
//                fileMeetup.setName(fileMeetupDTO.getName());
//                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
//
//                // ניהול הקטגוריה
//                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGalleryCategoryId());
//                if (category.isPresent()) {
//                    fileMeetup.setGalleryCategory(category.get());
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//
//                // טיפול בקבצים לפי סוג
//                if (fileMeetupDTO.getTypeFile().equals("image")) {
//                    // אם זה תמונה, שמירה בשרת כקובץ תמונה
//                    Path pathFile = Paths.get(File_Meetup + file.getOriginalFilename());
//                    Files.write(pathFile, file.getBytes());
//
//                    // עדכון נתיב התמונה במסד הנתונים
//                    fileMeetup.setUrl_file(file.getOriginalFilename());
//
//                    System.out.println("fileMeetupDTO.getUrl_file()");
//                    System.out.println(fileMeetup.getUrl_file());
//
//
//                } else if (fileMeetupDTO.getTypeFile().equals("video")) {
//                    // אם זה סרטון, שמירת ה-URL של הסרטון (לא שומרים את הקובץ עצמו)
//                    System.out.println("fileMeetupDTO.getUrl_file()");
//                    System.out.println(fileMeetupDTO.getUrl_file());
//                    fileMeetup.setUrl_file(fileMeetupDTO.getUrl_file());
//                }
//
//                // שמירת האובייקט במסד הנתונים
//                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
//
//                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
//            }

            //
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
//                    String videoUrl = fileMeetupDTO.getUrl_file();
//                    if (videoUrl != null && !videoUrl.isEmpty()) {
//                        fileMeetup.setUrl_file(videoUrl);
//                        System.out.println("Video URL: " + videoUrl);
//                    } else {
//                        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//                    }
                }

                // שמירת האובייקט במסד הנתונים
                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);

                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }




            //GET
            //10-12-24
//            @GetMapping("/getAllMeetupFile1")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile1() throws IOException {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                for (FileMeetup fileMeetup : fileMeetups) {
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//                    fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//
//                    // אם ה-URL של התמונה קיים, המרתו למערך ביטים
//                    if (fileMeetup.getUrl_file() != null) {
//                        Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());
//                        byte[] fileData = Files.readAllBytes(path);
//                        fileMeetupDTO.setFileData(fileData);
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }

            //נסיון לשנות כך שיתאים לתמונות ווידיאו
            //GPT
            //עובד אבל מחזיר מתשנה כפול..
//            @GetMapping("/getAllMeetupFile1")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile1() throws IOException {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                for (FileMeetup fileMeetup : fileMeetups) {
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//                    fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//
//                    // טיפול לפי סוג הקובץ
//                    if (fileMeetup.getTypeFile().equals("image")) {
//                        // אם זה תמונה, המרת ה-URL למערך ביטים
//                        if (fileMeetup.getUrl_file() != null) {
//                            Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());
//                            byte[] fileData = Files.readAllBytes(path);
//                            fileMeetupDTO.setFileData(fileData);
//                        }
//                    } else if (fileMeetup.getTypeFile().equals("video")) {
//                        // אם זה סרטון, מחזירים רק את ה-URL של הסרטון
//                        fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }
//
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
//            }

            //שינוי כדי שלא יחזיר משתנה כפול..
            //עובד פגזזזזזזזזזז
            //מזומור לתודה
            //עשיתי עם פונקציה נוספת במקום לעשות את ההמרה בתוך הפונ..
//            @GetMapping("/getAllMeetupFile1")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile1() throws IOException {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll(); // מקבלים את כל הרשומות
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>(); // רשימה לשמירת ה-DTOs
//
//                for (FileMeetup fileMeetup : fileMeetups) {
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile()); // סוג הקובץ
//                    fileMeetupDTO.setGalleryCategoryId(fileMeetup.getGalleryCategory().getId()); // מזהה הקטגוריה
//
//                    // טיפול לפי סוג הקובץ
//                    if ("image".equals(fileMeetup.getTypeFile())) {
//                        // אם זה תמונה, המרת ה-URL למערך ביטים
//                        if (fileMeetup.getUrl_file() != null) {
//                            Path path = Paths.get(File_Meetup + fileMeetup.getUrl_file());
//                            if (Files.exists(path)) {
//                                byte[] fileData = Files.readAllBytes(path); // קריאת הקובץ לביטים
//                                fileMeetupDTO.setFileData(fileData); // הגדרת נתוני הקובץ
//                            } else {
//                                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // אם הקובץ לא נמצא
//                            }
//                        }
//                    } else if ("video".equals(fileMeetup.getTypeFile())) {
//                        // אם זה סרטון, מחזירים רק את ה-URL של הסרטון
//                        fileMeetupDTO.setUrl_file(fileMeetup.getUrl_file());
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO); // הוספת ה-DTO לרשימה
//                }
//
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK); // מחזירים את כל ה-DTOs
//            }

            //עובד ב"ה
            //לא צריך??who say that??
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

            //נסיון
//            @GetMapping("/getAllMeetupFile")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile() throws IOException{
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                // יצירת רשימה חדשה של FileMeetupDTO
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                // הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
//                for (FileMeetup fileMeetup : fileMeetups) {
//
//                    Path path = Paths.get(fileMeetup.getUrl_file());
//                    byte[]arr=Files.readAllBytes(path);
//
//
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//
//                    // אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא
//                    if (fileMeetup.getUrl_file() != null) {
//                        fileMeetupDTO.setFile(fileMeetup.getUrl_file());
//                    }
//
//                    // הוספת ID של הקטגוריה אם קיים
//                    if (fileMeetup.getGallery_category() != null) {
//                        fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//                    }
//
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }
//
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
//            }

            //נסיון של GPT
//            @GetMapping("/getAllMeetupFile")
//            public ResponseEntity<List<FileMeetupDTO>> getAllMeetupFile() throws IOException {
//                // שליפת כל רשומות ה-FileMeetup
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findAll();
//
//                // יצירת רשימה חדשה של FileMeetupDTO
//                List<FileMeetupDTO> fileMeetupDTOs = new ArrayList<>();
//
//                // הוספת כל האובייקטים עם הנתיב המלא של התמונה ל-DTO
//                for (FileMeetup fileMeetup : fileMeetups) {
//                    FileMeetupDTO fileMeetupDTO = new FileMeetupDTO();
//                    fileMeetupDTO.setId(fileMeetup.getId());
//                    fileMeetupDTO.setName(fileMeetup.getName());
//                    fileMeetupDTO.setTypeFile(fileMeetup.getTypeFile());
//
//                    // אם ה-URL של התמונה קיים, נוסיף את הנתיב המלא לתמונה
//                    if (fileMeetup.getUrl_file() != null) {
//                        Path path = Paths.get(fileMeetup.getUrl_file());
//
//                        // קריאת התמונה לקובץ של ביטים
//                        byte[] arr = Files.readAllBytes(path);
//
//                        // הגדרת התמונה כ- byte[] ב DTO
//                        fileMeetupDTO.setFile(arr);
//                    }
//
//                    // הוספת ID של הקטגוריה אם קיים
//                    if (fileMeetup.getGallery_category() != null) {
//                        fileMeetupDTO.setGallery_category_id(fileMeetup.getGallery_category().getId());
//                    }
//
//                    // הוספת ה-DTO לרשימה
//                    fileMeetupDTOs.add(fileMeetupDTO);
//                }
//
//                // החזרת רשימת ה-DTOs
//                return new ResponseEntity<>(fileMeetupDTOs, HttpStatus.OK);
//            }



            //Post-add
            //לא צריך
            @PostMapping("/addFileMeetup")
            public ResponseEntity<FileMeetup> addFileMeetup(@RequestBody FileMeetup galleryCategory) {
                FileMeetup newFileMeetup = fileMeetupRepository.save(galleryCategory);
                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
            }

            //לא צריך כי הפונקציה של הוספת תמונה עושה הכל ביחד
//            @PostMapping("/postFileMeetup")
//            public ResponseEntity<FileMeetup> postFileMeetup(@RequestBody FileMeetupDTO fileMeetupDTO) {
//                // Create a new article
//                FileMeetup fileMeetup = new FileMeetup();
//                fileMeetup.setName(fileMeetupDTO.getName());
//                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
//                fileMeetup.setUrl_file(fileMeetupDTO.getUrl_file());
//
//
//                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGallery_category_id());
//                if (category.isPresent())
//                {
//                    fileMeetup.setGallery_category(category.get()); // Set the existing category as the relationship
//                }
//                else
//                {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//
//                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
//                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
//            }


            ////-file/-------------------------------------------------------
//                @PostMapping("/addMeetupFile")
//                public ResponseEntity<FileMeetup> addMeetupFile(@RequestPart("fileFileMeetup") FileMeetup fileMeetup,
//                                                             @RequestPart("file") MultipartFile imageFile) throws IOException {
//                    //הניתוב במלא של התמונה +הסימות
//                    Path pathFile = Paths.get(File_Meetup + imageFile.getOriginalFilename());
//                    //שמירת התמונה בנתיב
//                    Files.write(pathFile, imageFile.getBytes());
//                    //מעדכנת את הניץוב של התמונה בDATA
//                    fileMeetup.setUrl_file(imageFile.getOriginalFilename());
//
//                    FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
//
//                    return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
//                }


            ///נסיון לששלב את 2 הפונקציות ביחד גם הוספה של DTO וגם הוספת תמונה רגילה...
            //הצלחתי בפוסטמן!!!!!!!!!!!
//            @PostMapping("/addMeetupFile")
//            public ResponseEntity<FileMeetup> addMeetupFile(@RequestPart("fileFileMeetup") FileMeetupDTO fileMeetupDTO,
//                                                            @RequestPart("file") MultipartFile imageFile) throws IOException {
//
//                FileMeetup fileMeetup = new FileMeetup();
//                fileMeetup.setName(fileMeetupDTO.getName());
//                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
//                fileMeetup.setUrl_file(fileMeetupDTO.getFile().toString());
//
//                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGallery_category_id());
//                if (category.isPresent())
//                {
//                    fileMeetup.setGallery_category(category.get()); // Set the existing category as the relationship
//                }
//                else
//                {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//
//                //הניתוב במלא של התמונה +הסימות
//                Path pathFile = Paths.get(File_Meetup + imageFile.getOriginalFilename());
//                //שמירת התמונה בנתיב
//                Files.write(pathFile, imageFile.getBytes());
//                //מעדכנת את הניץוב של התמונה בDATA
//                fileMeetup.setUrl_file(imageFile.getOriginalFilename());
//
//                FileMeetup newFileMeetup = fileMeetupRepository.save(fileMeetup);
//
//                return new ResponseEntity<>(newFileMeetup, HttpStatus.CREATED);
//            }


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


            //פונקציה שמחזירה לי את כל הFILE MEETUP לפי הID של הקטגוריה
//            @GetMapping("/getFileMeetupByGalleryCategoryId/{gallery_category_id}")
//            public ResponseEntity<List<FileMeetup>>getFileMeetupByGalleryCategoryId(@PathVariable Long gallery_category_id) {
//                List<FileMeetup> fileMeetups=fileMeetupRepository.findByMeetupId(gallery_category_id);
//                return new ResponseEntity<>(fileMeetups, HttpStatus.OK);
//            }

            //תיקון של GPT
            //הפונ לא עובדת!!!!!!!!!
//            @GetMapping("/getFileMeetupsByGalleryCategoryId/{galleryCategoryId}")
//            public ResponseEntity<List<FileMeetup>> getFileMeetupsByGalleryCategoryId(@PathVariable Long galleryCategoryId) {
//                List<FileMeetup> fileMeetups = fileMeetupRepository.findFileMeetupByGalleryId(galleryCategoryId);
//                return new ResponseEntity<>(fileMeetups, HttpStatus.OK);
//            }

            //מחזיר את כל המיטאפיים לפי ID של קטגןריה  גלרייה מסומית
            //עובד ב"ה
            //11-12-24
//            @GetMapping("/getFileMeetupsByGalleryCategoryId/{galleryCategoryId}")
//            public ResponseEntity<List<FileMeetup>>getFileMeetupsByGalleryCategoryId(@PathVariable Long galleryCategoryId){
//               List<FileMeetup> fileMeetups = fileMeetupRepository.findByGalleryCategory_Id(galleryCategoryId);
//               return new ResponseEntity<>(fileMeetups, HttpStatus.OK);
//            }

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


//            private FileMeetup MapFileMeetup(FileMeetupDTO fileMeetupDTO,MultipartFile file) {
//                FileMeetup fileMeetup = new FileMeetup();
//                fileMeetup.setName(fileMeetupDTO.getName());
//                fileMeetup.setTypeFile(fileMeetupDTO.getTypeFile());
//
//                // ניהול הקטגוריה
//                Optional<GalleryCategory> category = galleryCategoryRepository.findById(fileMeetupDTO.getGalleryCategoryId());
//                if (category.isPresent()) {
//                    fileMeetup.setGalleryCategory(category.get());
//                }
//
//                // טיפול בקבצים לפי סוג
//                if (fileMeetupDTO.getTypeFile().equals("image")) {
//                    // אם זה תמונה, שמירה בשרת כקובץ תמונה
//                    Path pathFile = Paths.get(File_Meetup + file.getOriginalFilename());
//                    Files.write(pathFile, file.getBytes());
//
//                    // עדכון נתיב התמונה במסד הנתונים
//                    fileMeetup.setUrl_file(file.getOriginalFilename());
//                } else if (fileMeetupDTO.getTypeFile().equals("video")) {
//                    // אם זה סרטון, שמירת ה-URL של הסרטון (לא שומרים את הקובץ עצמו)
//                    fileMeetup.setUrl_file(fileMeetupDTO.getUrl_file());
//                }
//                return fileMeetup;
//
//            }

            //פגזזזזזזזזזזזזזזזזז
            //11-12-24
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
