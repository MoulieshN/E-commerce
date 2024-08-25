package com.mouli.Authentication_Service.Security.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.mouli.Authentication_Service.Models.User;
import com.mouli.Authentication_Service.Repository.UserRepository;
import com.mouli.Authentication_Service.Security.model.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    UserRepository userRepository;
    CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user.get());
    }
}
