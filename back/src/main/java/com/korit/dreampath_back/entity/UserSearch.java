package com.korit.dreampath_back.entity;

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
public class UserSearch {
    private int userId;
    private String username;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String oAuth2Name;
    private String oAuth2Provider;
    private int userPurchase;
    private Set<UserRole> userRoles;

    private LocalDateTime createdAt;
}
