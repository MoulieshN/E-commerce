package com.mouli.Authentication_Service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        lastUpdateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateAt = new Date();
    }

}
