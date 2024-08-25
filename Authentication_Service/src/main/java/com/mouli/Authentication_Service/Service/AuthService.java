package com.mouli.Authentication_Service.Service;

import com.mouli.Authentication_Service.Dtos.UserDto;
import com.mouli.Authentication_Service.Exceptions.NotFoundException;
import com.mouli.Authentication_Service.Exceptions.UnAuthorizedException;
import com.mouli.Authentication_Service.Exceptions.UserAlreadyExistException;
import com.mouli.Authentication_Service.Models.Session;
import com.mouli.Authentication_Service.Models.SessionStatus;
import com.mouli.Authentication_Service.Models.User;
import com.mouli.Authentication_Service.Models.Role;
import com.mouli.Authentication_Service.Repository.SessionRepository;
import com.mouli.Authentication_Service.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


import javax.management.relation.RoleStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto signUp(String email, String password) throws UserAlreadyExistException {
        Optional<User>  user = userRepository.findByEmail(email);

        if(!user.isEmpty()){
            throw new UserAlreadyExistException("User with  " + email + " already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(email);

        // Add a default role
        Role role = new Role();
        role.setRole("ADMIN");

        newUser.setPassword(passwordEncoder.encode(password)); // encrpt and dcrypt the password usign Bcryptencoder

        userRepository.save(newUser);

        return UserDto.fromEntity(newUser);
    }


    public ResponseEntity<UserDto> login(String email, String password) throws NotFoundException, UnAuthorizedException{
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new NotFoundException("User with email " + email + " not found");
        }

        User existingUser = userOptional.get();


        // Check if the password matches using the password encoder
        if(!passwordEncoder.matches(password, existingUser.getPassword())){
            throw new UnAuthorizedException("Unauthorized user");
        }


        // Generate a random token
        String token = RandomStringUtils.randomAscii(20);
        MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
        headers.add("AUTH_TOKEN", token);

        Map<String, String> claims = new HashMap<>();
        claims.put("email", existingUser.getEmail());
        claims.put("name", existingUser.getName());
        claims.put("role", "admin");

//        String jwtToken = Jwts.builder();

        //Has to store the token in the session db
        Session session = new Session();
        session.setToken(token);
        session.setUser(existingUser);
        session.setSessionStatus(SessionStatus.ACTIVE);

        // Save the session
        sessionRepository.save(session);

        UserDto userDto = UserDto.fromEntity(existingUser);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    public Optional<UserDto> validate(String token, Long userID) throws NotFoundException, UnAuthorizedException{
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUserId(token, userID);

        if(sessionOptional.isEmpty()){
            return Optional.empty();
        }

        Session session = sessionOptional.get();

        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)){
            return Optional.empty();
        }
        /*
        UserDto userDto = userRepository.findById(userID).map(UserDto::fromEntity).orElseThrow(() -> new NotFoundException("User not found"));
        */
        // Get the user from the user repository
        User user = userRepository.findById(userID).get();
        UserDto userDto = UserDto.fromEntity(user);

        // We can also check if the token has expired here
        // TODO: Implement token expiry logic

        return Optional.of(userDto);
    }
}
