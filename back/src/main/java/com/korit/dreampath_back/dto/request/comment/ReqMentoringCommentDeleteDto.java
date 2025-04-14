package com.korit.dreampath_back.dto.request.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqMentoringCommentDeleteDto {

    @Schema(description = "멘토링 댓글 삭제 고유 ID")
    private int commentId;
    @Schema(description = "멘토링 댓글 삭제 사용자 ID")
    private int userId;


}
