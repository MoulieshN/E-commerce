package com.mouli.Authentication_Service.Controller;

import com.mouli.Authentication_Service.Dtos.SignUpRequestDto;
import com.mouli.Authentication_Service.Dtos.UserDto;
import com.mouli.Authentication_Service.Dtos.ValidateRequestDto;
import com.mouli.Authentication_Service.Dtos.ValidateResponseDto;
import com.mouli.Authentication_Service.Models.SessionStatus;
import com.mouli.Authentication_Service.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

//    @GetMapping("/login")
//    public String login(String email, String password){
//        return authUser.login(email, password);
//    }
//
//    @GetMapping("/validate")
//    public String validate(String token){
//        return authUser.validate(token);
//    }


    // ResponseEntity allows us to return a response with a status code
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) throws Exception{
        UserDto userDto = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody SignUpRequestDto request) throws Exception{
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDto> validate(@RequestBody ValidateRequestDto request) throws Exception{
        Optional<UserDto> userDtoOptional = authService.validate(request.getToken(), request.getUserId());

        ValidateResponseDto response = new ValidateResponseDto();

        if(userDtoOptional.isEmpty()){
            response.setSessionStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        response.setUserDto(userDtoOptional.get());
        response.setSessionStatus(SessionStatus.ACTIVE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
