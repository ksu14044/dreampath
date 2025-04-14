package com.korit.dreampath_back.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespTokenDto {

    @Schema(description = "타입")
    private String type;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "토큰")
    private String token;

}
