package com.korit.dreampath_back.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqApplyEmailDto {
    @Schema(description = "게시글 고유 ID")
    private int postId;
    @Schema(description = "멘토링 신청 요청 이메일")
    private String email;
}
