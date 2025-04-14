package com.korit.dreampath_back.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Builder
@Data
public class RespAuthPhoneDto {
    @Schema(description = "핸드폰 인증 코드")
    private String code;
}
