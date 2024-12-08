    package com.example.demo.controller;

    import com.example.demo.modle.Invitations_to_host_a_meetup_in_the_company;
    import com.example.demo.service.Invitations_to_host_a_meetup_in_the_companyRepository;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    @RequestMapping("api/Invitations_to_host_a_meetup_in_the_company")
    @RestController
    @CrossOrigin
    public class Invitations_to_host_a_meetup_in_the_companyController {

        private Invitations_to_host_a_meetup_in_the_companyRepository invitationsToHostAMeetupInTheCompanyRepository;

        public Invitations_to_host_a_meetup_in_the_companyController(Invitations_to_host_a_meetup_in_the_companyRepository invitationsToHostAMeetupInTheCompanyRepository) {
            this.invitationsToHostAMeetupInTheCompanyRepository = invitationsToHostAMeetupInTheCompanyRepository;
        }

        //Get By Id
        @GetMapping("/getInvitations_to_host_a_meetup_in_the_companyById/{id}")
        public ResponseEntity<Invitations_to_host_a_meetup_in_the_company> getInvitations_to_host_a_meetup_in_the_company(@PathVariable long id) {
            Invitations_to_host_a_meetup_in_the_company i= invitationsToHostAMeetupInTheCompanyRepository.findById(id).orElse(null);
            if (i== null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(i, HttpStatus.OK);
        }

        //Get All List
        @GetMapping("/getAllInvitations_to_host_a_meetup_in_the_company")
        public ResponseEntity<List<Invitations_to_host_a_meetup_in_the_company>> getAllInvitations_to_host_a_meetup_in_the_company() {
            return new ResponseEntity<>(invitationsToHostAMeetupInTheCompanyRepository.findAll(), HttpStatus.OK);
        }

        //Post-add
        @PostMapping("/addInvitations_to_host_a_meetup_in_the_company")
        public ResponseEntity<Invitations_to_host_a_meetup_in_the_company> addInvitations_to_host_a_meetup_in_the_company(@RequestBody Invitations_to_host_a_meetup_in_the_company invitationsToHostAMeetupInTheCompany) {
            Invitations_to_host_a_meetup_in_the_company  newInvitations_host_a_meetup_in_the_company = invitationsToHostAMeetupInTheCompanyRepository.save(invitationsToHostAMeetupInTheCompany);
            return new ResponseEntity<>(newInvitations_host_a_meetup_in_the_company, HttpStatus.CREATED);
        }

        //Put
        @PutMapping("/updateInvitations_to_host_a_meetup_in_the_company/{id}")
        public ResponseEntity<Invitations_to_host_a_meetup_in_the_company> updateInvitations_to_host_a_meetup_in_the_company(@PathVariable long id, @RequestBody Invitations_to_host_a_meetup_in_the_company invitationsToHostAMeetupInTheCompany) {
            if(id != invitationsToHostAMeetupInTheCompany.getId()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            invitationsToHostAMeetupInTheCompanyRepository.save(invitationsToHostAMeetupInTheCompany);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        //Deletet
        @DeleteMapping("/deleteInvitations_to_host_a_meetup_in_the_company/{id}")
        public ResponseEntity<Invitations_to_host_a_meetup_in_the_company> deleteInvitations_to_host_a_meetup_in_the_company(@PathVariable long id) {
            invitationsToHostAMeetupInTheCompanyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
