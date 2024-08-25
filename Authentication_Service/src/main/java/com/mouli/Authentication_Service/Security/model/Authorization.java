package com.mouli.Authentication_Service.Security.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "`authorization`")
public class Authorization {
    @Id
    @Column
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    @Column(length = 1000)
    @Lob // for text larger than 255 characters
    private String authorizedScopes;
    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String attributes;
    @Column(length = 500)
    @Lob // for text larger than 255 characters
    private String state;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String accessTokenMetadata;
    private String accessTokenType;
    @Column(length = 1000)
    @Lob // for text larger than 255 characters
    private String accessTokenScopes;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String refreshTokenMetadata;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String oidcIdTokenMetadata;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String oidcIdTokenClaims;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String userCodeValue;
    private Instant userCodeIssuedAt;
    private Instant userCodeExpiresAt;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String userCodeMetadata;

    @Column(length = 4000)
    @Lob // for text larger than 255 characters
    private String deviceCodeValue;
    private Instant deviceCodeIssuedAt;
    private Instant deviceCodeExpiresAt;
    @Column(length = 2000)
    @Lob // for text larger than 255 characters
    private String deviceCodeMetadata;

}
