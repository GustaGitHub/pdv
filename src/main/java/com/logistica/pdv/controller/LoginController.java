package com.logistica.pdv.controller;

import com.logistica.pdv.DTO.LoginDTO;
import com.logistica.pdv.DTO.TokenDTO;
import com.logistica.pdv.security.CustomUserDetailService;
import com.logistica.pdv.security.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomUserDetailService _customUserDetailService;

    @Autowired
    private JWTService _jwtService;

    @PostMapping()
    public ResponseEntity loginUser(@RequestBody LoginDTO newLogin){
        try {
            _customUserDetailService.checkCredentialsUser(newLogin);
            TokenDTO response = _jwtService.generateToken(newLogin.getUsername().trim());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

}
