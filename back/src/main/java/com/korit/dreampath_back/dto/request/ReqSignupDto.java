package com.korit.dreampath_back.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Valid
public class ReqSignupDto {
    @Min(value = 1, message = "roleId 유효성 실패")
    @Schema(description = "분류 ID")
    private int roleId;
    @Schema(description = "사용자이름")
    @Pattern(regexp="^[a-zA-Z0-9]{3,20}$",
            message = "사용자이름은 3자 이상, 20자 이하이며, 알파벳과 숫자만 포함해야 합니다.")
    private String username;
    @Schema(description = "비밀번호")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,20}$",
            message = "비밀번호는 8자 이상, 20자 이하이며, 숫자와 특수문자를 포함해야 합니다.")
    private String password;
    @Email
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "닉네임")
    private String nickname;
}
