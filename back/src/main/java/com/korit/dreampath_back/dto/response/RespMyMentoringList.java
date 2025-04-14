package com.korit.dreampath_back.dto.response;

import com.korit.dreampath_back.entity.MyMentoringSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespMyMentoringList {
    @Schema(description = "멘토링 페이지")
    private int page;
    @Schema(description = "멘토링 개수 제한")
    private int limitCount;
    @Schema(description = "멘토링 총 페이지")
    private int totalPages;
    @Schema(description = "총 멘토링 개수")
    private int totalElements;
    @Schema(description = "멘토링 첫 페이지")
    private boolean isFirstPage;
    @Schema(description = "멘토링 마지막 페이지")
    private boolean isLastPage;
    @Schema(description = "멘토링 다음 페이지")
    private int nextPage;
    private List<MyMentoringSearch> myMentoringSearchList;
}
