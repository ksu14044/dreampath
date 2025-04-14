package com.korit.dreampath_back.dto.response.post;

import com.korit.dreampath_back.entity.PostListPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "게시글 조회 응답 DTO")
public class RespPostListDto {
    @Schema(description = "게시글 조회 응답 페이지")
    private int page;
    @Schema(description = "게시글 조회 응답 개수 제한")
    private int limitCount;
    @Schema(description = "게시글 조회 응답 총 페이지")
    private int totalPages;
    @Schema(description = "총 게시글 조회 응답 개수")
    private int totalElements;
    @Schema(description = "게시글 조회 응답 첫 페이지")
    private boolean isFirstPage;
    @Schema(description = "게시글 조회 응답 마지막 페이지")
    private boolean isLastPage;
    @Schema(description = "게시글 조회 응답 다음 페이지")
    private int nextPage;
    private List<PostListPage> postList;
}
