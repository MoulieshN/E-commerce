package com.mouli.Authentication_Service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "sessions")
@Getter
@Setter
public class Session extends BaseModel{
    private String token;
    @ManyToOne(cascade  = {CascadeType.REMOVE})
    private User user;
    private Date expiringAt;

    @Enumerated(EnumType.ORDINAL) // ORDINAL allows us store the sessionStatus in INT whereas the ENUMTYPE.STRING stores actual string eg: ACTIVE (lot more space)
    private SessionStatus sessionStatus;
}
