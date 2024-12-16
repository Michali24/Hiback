        package com.example.demo.controller;


        import com.example.demo.dto.MeetupSchedleDTO;
        import com.example.demo.modle.FileMeetup;
        import com.example.demo.modle.MeetapimSchedule;
        import com.example.demo.service.MeetapimScheduleRepository;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

        @RequestMapping("api/MeetapimSchedule")
        @RestController
        @CrossOrigin
        public class MeetapimScheduleController {

            private MeetapimScheduleRepository meetapimScheduleRepository;

            private static String File_MeetupSchedule =System.getProperty("user.dir")+"//imgesMeetup//";


            public MeetapimScheduleController(MeetapimScheduleRepository meetapimScheduleRepository) {
                this.meetapimScheduleRepository = meetapimScheduleRepository;
            }

            //Get By Id
            //לא בשימוש באתר...
//            @GetMapping("/getMeetapimScheduleById/{id}")
//            public ResponseEntity<MeetapimSchedule> getMeetapimSchedule(@PathVariable long id) {
//                MeetapimSchedule m = meetapimScheduleRepository.findById(id).orElse(null);
//                if (m== null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                return new ResponseEntity<>(m, HttpStatus.OK);
//            }

            //נסיון יש שגיאה..
//            @GetMapping("/getMeetapimScheduleById/{id}")
//            public ResponseEntity<MeetupSchedleDTO> getMeetapimSchedule(@PathVariable long id) {
//                MeetapimSchedule m = meetapimScheduleRepository.findById(id).orElse(null);
//                if (m== null) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                MeetupSchedleDTO meetupSchedleDTO=MapMeetupSchedule(m);
//
//                return new ResponseEntity<>(meetupSchedleDTO, HttpStatus.OK);
//            }

            //ב"ה עובד פצצה
            //לא רואה כרגע שימוש לזה באתר
            @GetMapping("/getMeetapimScheduleById/{id}")
            public ResponseEntity<MeetupSchedleDTO> getMeetapimSchedule(@PathVariable long id) {
                try {
                    MeetapimSchedule m = meetapimScheduleRepository.findById(id).orElse(null);
                    if (m == null) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                    MeetupSchedleDTO meetupSchedleDTO = MapMeetupSchedule(m);

                    return new ResponseEntity<>(meetupSchedleDTO, HttpStatus.OK);
                } catch (IOException e) {
                    // טיפול בשגיאה אם קריאת הקובץ לא מצליחה
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            //Get All List
            //עובד!!!!!!!!!!!!!!!
            //10-12-24
//            @GetMapping("/getAllMeetapimSchedule")
//            public ResponseEntity<List<MeetupSchedleDTO>> getAllMeetapimSchedule() throws IOException {
//                List<MeetapimSchedule> m = meetapimScheduleRepository.findAll();
//                List<MeetupSchedleDTO> dtos = new ArrayList<>();
//                for (MeetapimSchedule ms : m) {
//                    Path path = Paths.get(File_MeetupSchedule + ms.getPoster_img_meetup());
//                    byte[] arr = Files.readAllBytes(path);
//
//                    MeetupSchedleDTO meetupSchedleDTO = new MeetupSchedleDTO();
//                    meetupSchedleDTO.setId(ms.getId());
//                    meetupSchedleDTO.setMeetupDate(ms.getMeetupDate());
//                    meetupSchedleDTO.setNameOfTheHostCompany(ms.getNameOfTheHostCompany());
//                    meetupSchedleDTO.setAddressHostCompany(ms.getAddressHostCompany());
//                    meetupSchedleDTO.setTimeOfTheMeetup(ms.getTimeOfTheMeetup());
//                    meetupSchedleDTO.setWhoIsthemeetupfor(ms.getWhoIsthemeetupfor());
//                    meetupSchedleDTO.setPoster_img_meetup(arr);
//
//                    dtos.add(meetupSchedleDTO);
//                }
//                return new ResponseEntity<>(dtos, HttpStatus.OK);
//            }

            //11-12-24
            //בה עובד עם הפונ המקוצרת
            @GetMapping("/getAllMeetapimSchedule")
            public ResponseEntity<List<MeetupSchedleDTO>> getAllMeetapimSchedule() throws IOException {
                List<MeetapimSchedule> m = meetapimScheduleRepository.findAll();
                List<MeetupSchedleDTO> dtos = new ArrayList<>();
                for (MeetapimSchedule ms : m) {
                    MeetupSchedleDTO meetupSchedleDTO = MapMeetupSchedule(ms);
                    dtos.add(meetupSchedleDTO);
                }
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            }


            //Get Last Added Meetup
//            @GetMapping("/getLastMeetapimSchedule")
//            public ResponseEntity<MeetapimSchedule> getLastMeetapimSchedule() {
//                //שליפת המיטאפ האחרון שנוסף
//                Optional<MeetapimSchedule> lastMeetapimSchedule = meetapimScheduleRepository.findTopByOrderByIdDesc();
//
//                if (lastMeetapimSchedule.isEmpty()) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//
//                return new ResponseEntity<>(lastMeetapimSchedule.get(), HttpStatus.OK);
//            }



            //נסיון
            //10-12-24
            //ב"ה מזמור לתודה עובד!!!!!!!!!
//            @GetMapping("/getLastMeetapimSchedule")
//            public ResponseEntity<MeetupSchedleDTO> getLastMeetapimSchedule() throws IOException {
//                // שליפת המיטאפ האחרון שנוסף
//                Optional<MeetapimSchedule> lastMeetapimSchedule = meetapimScheduleRepository.findTopByOrderByIdDesc();
//
//                // אם אין מיטאפ כזה, מחזירים שגיאה 404
//                if (lastMeetapimSchedule.isEmpty()) {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//
//                // קריאת המיטאפ
//                MeetapimSchedule ms = lastMeetapimSchedule.get();
//
//                // אם הפוסטר הוא קובץ מקומי
//                Path path = Paths.get(File_MeetupSchedule + ms.getPoster_img_meetup());
//                byte[] arr = Files.readAllBytes(path);
//
//                // יצירת DTO
//                MeetupSchedleDTO meetupSchedleDTO = new MeetupSchedleDTO();
//                meetupSchedleDTO.setId(ms.getId());
//                meetupSchedleDTO.setMeetupDate(ms.getMeetupDate());
//                meetupSchedleDTO.setNameOfTheHostCompany(ms.getNameOfTheHostCompany());
//                meetupSchedleDTO.setAddressHostCompany(ms.getAddressHostCompany());
//                meetupSchedleDTO.setTimeOfTheMeetup(ms.getTimeOfTheMeetup());
//                meetupSchedleDTO.setWhoIsthemeetupfor(ms.getWhoIsthemeetupfor());
//                meetupSchedleDTO.setPoster_img_meetup(arr);
//
//                // החזרת התשובה
//                return new ResponseEntity<>(meetupSchedleDTO, HttpStatus.OK);
//            }

            //11-12-24
            //ב"ה עובד מעולה עם הפונקציה המקוצרת!!
            @GetMapping("/getLastMeetapimSchedule")
            public ResponseEntity<MeetupSchedleDTO> getLastMeetapimSchedule() throws IOException {
                // שליפת המיטאפ האחרון שנוסף
                Optional<MeetapimSchedule> lastMeetapimSchedule = meetapimScheduleRepository.findTopByOrderByIdDesc();

                // אם אין מיטאפ כזה, מחזירים שגיאה 404
                if (lastMeetapimSchedule.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                // קריאת המיטאפ
                MeetapimSchedule ms = lastMeetapimSchedule.get();
                MeetupSchedleDTO meetupSchedleDTO=MapMeetupSchedule(ms);

                // החזרת התשובה
                return new ResponseEntity<>(meetupSchedleDTO, HttpStatus.OK);
            }

            //Post-add
//            @PostMapping("/addMeetapimSchedule")
//            public ResponseEntity<MeetapimSchedule> addMeetapimSchedule(@RequestBody MeetapimSchedule meetapimSchedule) {
//                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);
//                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
//            }

            //אין צורך בזה
            @PostMapping("/addMeetupSchedule")
            public ResponseEntity<MeetapimSchedule> addMeetupSchedule(@RequestBody MeetapimSchedule meetapimSchedule) {
                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);
                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
            }

            ////-file/-------------------------------------------------------
            //עובד!!!!!!!!!!!!!!!!!!!!
            @PostMapping("/addMeetapimScheduleFile")
            public ResponseEntity<MeetapimSchedule> addMeetapimScheduleFile(@RequestPart("fileMeetapimSchedule") MeetapimSchedule meetapimSchedule,
                                                                            @RequestPart("file") MultipartFile imageFile) throws IOException {
                //הניתוב במלא של התמונה +הסימות
                Path pathFile = Paths.get(File_MeetupSchedule + imageFile.getOriginalFilename());
                //שמירת התמונה בנתיב
                Files.write(pathFile, imageFile.getBytes());
                //מעדכנת את הניץוב של התמונה בDATA
                meetapimSchedule.setPoster_img_meetup(imageFile.getOriginalFilename());

                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);

                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
            }

            //Put
        //    @PutMapping("/updateMeetapimSchedule/{id}")
        //    public ResponseEntity<MeetapimSchedule> updateMeetapimSchedule(@PathVariable long id, @RequestBody MeetapimSchedule meetapimSchedule) {
        //        if(id != meetapimSchedule.getId()) {
        //            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //        }
        //        meetapimScheduleRepository.save(meetapimSchedule);
        //        return new ResponseEntity<>(HttpStatus.OK);
        //    }

            //Deletet
            @DeleteMapping("/deleteMeetapimSchedule/{id}")
            public ResponseEntity<MeetapimSchedule> deleteMeetapimSchedule(@PathVariable Long id) {
                meetapimScheduleRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //11-12-24
            //עובד ב"ה
            private  MeetupSchedleDTO MapMeetupSchedule(MeetapimSchedule meetapimSchedule) throws IOException{

                Path path = Paths.get(File_MeetupSchedule + meetapimSchedule.getPoster_img_meetup());
                byte[] arr = Files.readAllBytes(path);

                MeetupSchedleDTO meetupSchedleDTO = new MeetupSchedleDTO();

                meetupSchedleDTO.setId(meetapimSchedule.getId());
                meetupSchedleDTO.setLocalmeetupDate(meetapimSchedule.getLocalmeetupDate());
                meetupSchedleDTO.setNameOfTheHostCompany(meetapimSchedule.getNameOfTheHostCompany());
                meetupSchedleDTO.setAddressHostCompany(meetapimSchedule.getAddressHostCompany());
                meetupSchedleDTO.setTimeOfTheMeetup(meetapimSchedule.getTimeOfTheMeetup());
                meetupSchedleDTO.setWhoIsthemeetupfor(meetapimSchedule.getWhoIsthemeetupfor());
                meetupSchedleDTO.setPoster_img_meetup(arr);

                return meetupSchedleDTO;

            }

        }
