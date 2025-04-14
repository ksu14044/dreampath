package com.korit.dreampath_back.dto.request.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqMentoringCommentUpdateDto {

    @Schema(description = "멘토링 후기 고유 ID")
    private int commentId;
    @Schema(description = "멘토링 후기")
    private String content;
    @Schema(description = "멘토링 후기 평점")
    private int starPoint;

}
