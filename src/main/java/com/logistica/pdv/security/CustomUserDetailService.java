package com.logistica.pdv.security;

import com.logistica.pdv.DTO.LoginDTO;
import com.logistica.pdv.entity.User;
import com.logistica.pdv.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found");

        return new UserPrincipal(user);
    }

    public void checkCredentialsUser(LoginDTO login) throws Exception{
        var user = loadUserByUsername(login.getUsername());

        boolean passwordValid = SecurityConfig.passwordEncoder()
                .matches(login.getPassword(), user.getPassword());

        if(passwordValid == false){
            throw new Exception("Invalid Password");
        }

    }
}
