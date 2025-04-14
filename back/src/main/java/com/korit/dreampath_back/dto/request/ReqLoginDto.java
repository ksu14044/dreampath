package com.korit.dreampath_back.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "로그인 DTO")
public class ReqLoginDto {
    @Schema(description = "사용자이름")
    private String username;
    @Schema(description = "비밀번호")
    private String password;
}
