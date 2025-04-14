package com.korit.dreampath_back.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;

    private String username;
    private String roleName;
    @JsonIgnore
    private String password;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String profileImg;
    private int ticketId;
    private String oAuth2Name;
    private String oAuth2Provider;

    private int remaining;
    private double starPoint;
    private int remainPoint;

    private int accountExpired;
    private int accountLocked;
    private int credentialsExpired;
    private int accountEnabled;

    private Set<UserRole> userRoles;

    private LocalDateTime createdAt;


}
