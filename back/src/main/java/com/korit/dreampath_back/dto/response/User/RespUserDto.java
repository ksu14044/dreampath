package com.korit.dreampath_back.dto.response.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@Schema(description = "사용자 조회 수정 DTO")
public class RespUserDto {

    @Schema(description = "사용자 ID", example = "1")
    private int userId;

    @Schema(description = "사용자역할 ID", example = "1")
    private int roleId;

    @Schema(description = "사용자명", example = "user12")
    private String username;

    @Schema(description = "사용자 역할명", example = "멘토")
    private String roleName;

    @Schema(description = "닉네임", example = "nickname")
    private String nickname;

    @Schema(description = "이메일", example = "user12@naver.com")
    private String email;

    @Schema(description = "프로필 이미지", example = "profile.png")
    private String profileImg;

    @Schema(description = "별점", example = "5")
    private double starPoint;

    @Schema(description = "멘토링 갯수", example = "17")
    private int remaining;

    @Schema(description = "남은 포인트", example = "4000")
    private int remainPoint;

    @Schema(description = "가입 날짜")
    private LocalDateTime createdAt;
}
