package com.example.demo.controller;

import com.example.demo.modle.JoiningWhatsAppGroups;
import com.example.demo.service.JoiningWhatsAppGroupsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("api/JoiningWhatsAppGroups")
@RestController
@CrossOrigin
public class JoiningWhatsAppGroupsController {

    private JoiningWhatsAppGroupsRepository joiningWhatsAppGroupsRepository;

    public JoiningWhatsAppGroupsController(JoiningWhatsAppGroupsRepository joiningWhatsAppGroupsRepository) {
        this.joiningWhatsAppGroupsRepository = joiningWhatsAppGroupsRepository;
    }

    //Get By Id
    @GetMapping("/getArticlesById/{id}")
    public ResponseEntity<JoiningWhatsAppGroups> getJoiningWhatsAppGroups(@PathVariable long id) {
        JoiningWhatsAppGroups j= joiningWhatsAppGroupsRepository.findById(id).orElse(null);
        if (j== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(j, HttpStatus.OK);
    }

    //Get All List
    @GetMapping("/getAllJoiningWhatsAppGroups")
    public ResponseEntity<List<JoiningWhatsAppGroups>> getAllJoiningWhatsAppGroups() {
        return new ResponseEntity<>(joiningWhatsAppGroupsRepository.findAll(), HttpStatus.OK);
    }

    //Post-add
    @PostMapping("/addJoiningWhatsAppGroups")
    public ResponseEntity<JoiningWhatsAppGroups> addJoiningWhatsAppGroups(@RequestBody JoiningWhatsAppGroups joiningWhatsAppGroups) {
        JoiningWhatsAppGroups newJoiningWhatsAppGroups = joiningWhatsAppGroupsRepository.save(joiningWhatsAppGroups);
        return new ResponseEntity<>(newJoiningWhatsAppGroups, HttpStatus.CREATED);
    }

    //Put
    @PutMapping("/updateJoiningWhatsAppGroups/{id}")
    public ResponseEntity<JoiningWhatsAppGroups> updateJoiningWhatsAppGroups(@PathVariable long id, @RequestBody JoiningWhatsAppGroups joiningWhatsAppGroups) {
        if(id != joiningWhatsAppGroups.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        joiningWhatsAppGroupsRepository.save(joiningWhatsAppGroups);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Deletet
    @DeleteMapping("/deleteJoiningWhatsAppGroups/{id}")
    public ResponseEntity<JoiningWhatsAppGroups> deleteJoiningWhatsAppGroups(@PathVariable long id) {
        joiningWhatsAppGroupsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
