package com.mouli.Authentication_Service.Controller;

import com.mouli.Authentication_Service.Dtos.ErrorResponseDto;
import com.mouli.Authentication_Service.Exceptions.NotFoundException;
import com.mouli.Authentication_Service.Exceptions.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(e.getMessage());
        responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleUnAuthorizedException(UnAuthorizedException e){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(e.getMessage());
        responseDto.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

}
