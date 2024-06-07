package com.logistica.pdv.controller;

import com.logistica.pdv.DTO.NewUserDTO;
import com.logistica.pdv.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    private UserService _userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody NewUserDTO user){
        try{
            return new ResponseEntity<>(_userService.createUser(user), HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
