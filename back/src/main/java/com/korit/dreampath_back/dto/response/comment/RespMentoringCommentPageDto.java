package com.korit.dreampath_back.dto.response.comment;

import com.korit.dreampath_back.entity.CommentSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespMentoringCommentPageDto {

    @Schema(description = "멘토링 후기 페이지")
    private int page;
    @Schema(description = "멘토링 후기 개수 제한")
    private int limitCount;
    @Schema(description = "총 멘토링 후기 개수")
    private int totalElements;
    @Schema(description = "멘토링 후기 첫 페이지")
    private boolean isFirstPage; // 첫 페이지
    @Schema(description = "멘토링 후기 마지막 페이지")
    private boolean isLastPage; // 마지막 페이지
    @Schema(description = "멘토링 후기 총 페이지")
    private int totalPages; // 전체 페이지
    @Schema(description = "멘토링 후기 다음 페이지")
    private int nextPage; // 다음 페이지



    private List<CommentSearch> commentSearchList;

}
