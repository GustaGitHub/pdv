package com.logistica.pdv.service;

import com.logistica.pdv.DTO.NewUserDTO;
import com.logistica.pdv.entity.User;
import com.logistica.pdv.repository.IUserRepository;
import com.logistica.pdv.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    private IUserRepository _userRepository;

    public User createUser(NewUserDTO newUser){
        User user = new User();

        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setPassword(SecurityConfig.passwordEncoder().encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername().trim());

        return _userRepository.save(user);
    }


}
