        package com.example.demo.controller;


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
            @GetMapping("/getMeetapimScheduleById/{id}")
            public ResponseEntity<MeetapimSchedule> getMeetapimSchedule(@PathVariable long id) {
                MeetapimSchedule m = meetapimScheduleRepository.findById(id).orElse(null);
                if (m== null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(m, HttpStatus.OK);
            }

            //Get All List
            @GetMapping("/getAllMeetapimSchedule")
            public ResponseEntity<List<MeetapimSchedule>> getAllMeetapimSchedule() {
                return new ResponseEntity<>(meetapimScheduleRepository.findAll(), HttpStatus.OK);
            }


            //Get Last Added Meetup
            @GetMapping("/getLastMeetapimSchedule")
            public ResponseEntity<MeetapimSchedule> getLastMeetapimSchedule() {
                //שליפת המיטאפ האחרון שנוסף
                Optional<MeetapimSchedule> lastMeetapimSchedule = meetapimScheduleRepository.findTopByOrderByIdDesc();

                if (lastMeetapimSchedule.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(lastMeetapimSchedule.get(), HttpStatus.OK);
            }


            //Post-add
//            @PostMapping("/addMeetapimSchedule")
//            public ResponseEntity<MeetapimSchedule> addMeetapimSchedule(@RequestBody MeetapimSchedule meetapimSchedule) {
//                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);
//                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
//            }

            @PostMapping("/addMeetupSchedule")
            public ResponseEntity<MeetapimSchedule> addMeetupSchedule(@RequestBody MeetapimSchedule meetapimSchedule) {
                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);
                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
            }

            ////-file/-------------------------------------------------------
            @PostMapping("/addMeetapimScheduleFile")
            public ResponseEntity<MeetapimSchedule> uploadAuthorImage(@RequestPart("fileArticle") MeetapimSchedule meetapimSchedule,
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
            public ResponseEntity<MeetapimSchedule> deleteMeetapimSchedule(@PathVariable long id) {
                meetapimScheduleRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }
