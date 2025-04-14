package com.korit.dreampath_back.dto.request.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ReqMentoringCommentDto {

    @Schema(description = "게시글 고유 ID")
    private int postId;

    @Schema(description = "멘토링 후기")
    private String content;
    @Schema(description = "멘토링 후기 평점")
    private int starPoint;



}
