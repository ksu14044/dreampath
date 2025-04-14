package com.korit.dreampath_back.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "게시글 좋아요 요청 DTO")
public class ReqPostLikeDto {
    @Schema(description = "게시글 아이디")
    private int postId;
}
