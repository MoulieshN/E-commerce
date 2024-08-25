package com.mouli.Authentication_Service.Dtos;

import com.mouli.Authentication_Service.Models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
