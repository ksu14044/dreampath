package com.korit.dreampath_back.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqMyMentoringSearchDto {

    @Schema(description = "멘토링 페이지")
    private int page;
    @Schema(description = "멘토링 개수 제한")
    private int limitCount;
    @Schema(description = "멘토링 정렬")
    private String order;
    @Schema(description = "멘토링 검색")
    private String searchText;
}
