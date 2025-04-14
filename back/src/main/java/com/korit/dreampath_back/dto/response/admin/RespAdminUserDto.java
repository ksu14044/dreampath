package com.korit.dreampath_back.dto.response.admin;

import com.korit.dreampath_back.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespAdminUserDto {
    @Schema(description = "관리자 회원 조회 사용자 ID")
    private int userId;
    @Schema(description = "관리자 회원 조회 사용자 이름")
    private String username;
    @Schema(description = "관리자 회원 조회 이메일")
    private String email;
    @Schema(description = "관리자 회원 조회 닉네임")
    private String nickname;
    @Schema(description = "관리자 회원 조회 분류")
    private String roleType;
    @Schema(description = "관리자 회원 조회 핸드폰 번호")
    private String phoneNumber;
    @Schema(description = "회원 생성 날짜")
    private LocalDateTime createdAt;
    private int remainPoint;

    public RespAdminUserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.remainPoint = user.getRemainPoint();

        this.roleType = (user.getUserRoles() != null && !user.getUserRoles().isEmpty()) ?
                user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole() != null ? userRole.getRole().getRoleName() : "UNKNOWN_ROLE")
                        .collect(Collectors.joining(", "))
                : "Role이 없음";
    }
}
