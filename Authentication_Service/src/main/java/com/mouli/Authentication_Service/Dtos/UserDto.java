package com.mouli.Authentication_Service.Dtos;

import com.mouli.Authentication_Service.Models.Role;
import com.mouli.Authentication_Service.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;

    private Set<Role> roles = new HashSet<>();

    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
