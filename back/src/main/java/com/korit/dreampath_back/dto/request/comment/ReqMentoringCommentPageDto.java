package com.korit.dreampath_back.dto.request.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqMentoringCommentPageDto {

    @Schema(description = "멘토링 후기 페이지")
    private int page;
    @Schema(description = "멘토링 후기 개수 제한")
    private int limitCount;
}
