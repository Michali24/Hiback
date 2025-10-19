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
            
            @PostMapping("/addMeetupSchedule")
            public ResponseEntity<MeetapimSchedule> addMeetupSchedule(@RequestBody MeetapimSchedule meetapimSchedule) {
                MeetapimSchedule newMeetapimSchedule = meetapimScheduleRepository.save(meetapimSchedule);
                return new ResponseEntity<>(newMeetapimSchedule, HttpStatus.CREATED);
            }

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


            @DeleteMapping("/deleteMeetapimSchedule/{id}")
            public ResponseEntity<MeetapimSchedule> deleteMeetapimSchedule(@PathVariable Long id) {
                meetapimScheduleRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

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
