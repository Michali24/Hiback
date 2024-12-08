    package com.example.demo.controller;


    import com.example.demo.dto.ArticleDto;
    import com.example.demo.dto.JoiningMeetupDTO;
    import com.example.demo.modle.Articles;
    import com.example.demo.modle.CategoryOfArticles;
    import com.example.demo.modle.JoiningMeetup;
    import com.example.demo.modle.MeetapimSchedule;
    import com.example.demo.service.JoiningMeetupRepository;
    import com.example.demo.service.MeetapimScheduleRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RequestMapping("api/JoiningMeetup")
    @RestController
    @CrossOrigin
    public class JoiningMeetupController {

        @Autowired
        private JoiningMeetupRepository joiningMeetupRepository;

        @Autowired
        private MeetapimScheduleRepository meetapimScheduleRepository;

        public JoiningMeetupController(JoiningMeetupRepository joiningMeetupRepository) {
            this.joiningMeetupRepository = joiningMeetupRepository;
        }

        //Get By Id
        @GetMapping("/getArticlesById/{id}")
        public ResponseEntity<JoiningMeetup> getJoiningMeetup(@PathVariable long id) {
            JoiningMeetup j = joiningMeetupRepository.findById(id).orElse(null);
            if (j== null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(j, HttpStatus.OK);
        }

        //Get All List
        @GetMapping("/getAllJoiningMeetup")
        public ResponseEntity<List<JoiningMeetup>> getAllJoiningMeetup() {
            return new ResponseEntity<>(joiningMeetupRepository.findAll(), HttpStatus.OK);
        }

        //getJoiningMeetupByMeetupScheduleId
        @GetMapping("/getJoiningMeetupByMeetupScheduleId/{meetupScheduleId}")
        public ResponseEntity<List<JoiningMeetup>> getJoiningMeetupByMeetupScheduleId(@PathVariable Long meetupScheduleId) {
            List<JoiningMeetup> joiningMeetups = joiningMeetupRepository.findByMeetapimSchedule_Id(meetupScheduleId);
            return new ResponseEntity<>(joiningMeetups, HttpStatus.OK);
        }

        //Post-add
//        @PostMapping("/addJoiningMeetup")
//        public ResponseEntity<JoiningMeetup> addJoiningMeetup(@RequestBody JoiningMeetup joiningMeetup) {
//            JoiningMeetup newJoiningMeetup = joiningMeetupRepository.save(joiningMeetup);
//            return new ResponseEntity<>(newJoiningMeetup, HttpStatus.CREATED);
//        }

        //מוסיף בקשה להצטרפות לי מיטאפ מסויים
        @PostMapping("/addJoiningMeetup")
        public ResponseEntity<JoiningMeetup> addArticles(@RequestBody JoiningMeetupDTO joiningMeetupDTO) {
            // Create a new article
            JoiningMeetup joiningMeetup = new JoiningMeetup();
            joiningMeetup.setName(joiningMeetupDTO.getName());
            joiningMeetup.setFamilyName(joiningMeetupDTO.getFamilyName());
            joiningMeetup.setPhoneNumber(joiningMeetupDTO.getPhoneNumber());
            joiningMeetup.setRole(joiningMeetupDTO.getRole());
            joiningMeetup.setEmail(joiningMeetupDTO.getEmail());


            Optional<MeetapimSchedule> meetapimSchedule = meetapimScheduleRepository.findById(joiningMeetupDTO.getMeetapimSchedule_id());
            if (meetapimSchedule.isPresent())
            {
                joiningMeetup.setMeetapimSchedule(meetapimSchedule.get());// Set the existing category as the relationship
            }
            else
            {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            JoiningMeetup newJoiningMeetup = joiningMeetupRepository.save(joiningMeetup);
            return new ResponseEntity<>(newJoiningMeetup, HttpStatus.CREATED);
        }

        //Put
        @PutMapping("/updateJoiningMeetup/{id}")
        public ResponseEntity<JoiningMeetup> updateJoiningMeetup(@PathVariable long id, @RequestBody JoiningMeetup joiningMeetup) {
            if(id != joiningMeetup.getId()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            joiningMeetupRepository.save(joiningMeetup);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        //Deletet
        @DeleteMapping("/deleteJoiningMeetup/{id}")
        public ResponseEntity<JoiningMeetup> deleteJoiningMeetup(@PathVariable long id) {
            joiningMeetupRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
