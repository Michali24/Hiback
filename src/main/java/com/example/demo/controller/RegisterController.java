package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.modle.Register;
import com.example.demo.service.RegisterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
@RequestMapping("api/Register")
@RestController
@CrossOrigin
public class RegisterController {

    private RegisterRepository registerRepository;

    public RegisterController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    //Get By Id
    @GetMapping("/getRegisterById/{id}")
    public ResponseEntity<Register> getRegister(@PathVariable long id) {
        Register r = registerRepository.findById(id).orElse(null);
        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    //Get All List
    @GetMapping("/getAllRegister")
    public ResponseEntity<List<Register>> getAllRegister() {
        return new ResponseEntity<>(registerRepository.findAll(), HttpStatus.OK);
    }

//    //Post-add
//    @PostMapping("/addRegister")
//    public ResponseEntity<Register> addRegister(@RequestBody Register register) {
//        Register newRegister = registerRepository.save(register);
//        return new ResponseEntity<>(newRegister, HttpStatus.CREATED);
//    }

    @PostMapping("/addRegister")
    public ResponseEntity<Register> addUsers(@RequestBody Register register){
        Register user = registerRepository.findByName(register.getName());
        //לבדוק שאין כזה שם משתמש
        if (user == null) {
            Register u = registerRepository.save(register);
            return new ResponseEntity<>(u,HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    //להוסיף פונקציה שמכניסה משתמש
    //כאן צריך לבדוק האם שם המשתמש והסיסמא נכונים

    @PostMapping("Login")
    public ResponseEntity<UserDto> LogIn(@RequestBody Register register){
        ResponseEntity<UserDto> response = null;

        if (register.getName() == null || register.getPassword() == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Register user = registerRepository.findByName(register.getName());

            if (user != null && user.getPassword().equals(register.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setLastName(user.getLastName());
                userDto.setEmail(user.getEmail());
                userDto.setPhone(user.getPhone());
                userDto.setCompanyName(user.getCompanyName());
                userDto.setRoleInSociety(user.getRoleInSociety());

                response =  new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return response;
    }

    //Put
    @PutMapping("/updateRegister/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable long id, @RequestBody Register register) {
        if(id != register.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        registerRepository.save(register);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Deletet
    @DeleteMapping("/deleteRegister/{id}")
    public ResponseEntity<Register> deleteRegister(@PathVariable long id) {
        registerRepository.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
