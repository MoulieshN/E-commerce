package com.mouli.Authentication_Service.Security.model;

import com.mouli.Authentication_Service.Models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;
    CustomGrantedAuthority(Role role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
