package com.korit.dreampath_back.dto.request.User;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "사용자 조회 수정 DTO")
@Valid
public class ReqUserUpdateDto {


    @Schema(description = "사용자 ID", example = "1")
    private int userId;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,20}$",
            message = "비밀번호는 8자 이상, 20자 이하이며, 숫자와 특수문자를 포함해야 합니다.")
    @Schema(description = "비밀번호 수정", example = "newPass11")
    private String password;
    @Schema(description = "닉네임 수정", example = "newNick")
    private String nickname;
    @Email
    @Schema(description = "이메일 수정", example = "newEmail@naver.com")
    private String email;


}
