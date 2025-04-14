package com.korit.dreampath_back.dto.response;

import com.korit.dreampath_back.entity.PointPurchaseSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RespPointPurchaseListDto {
    @Schema(description = "포인트 구매내역 페이지")
    private int page;
    @Schema(description = "포인트 구매내역 개수 제한")
    private int limitCount;
    @Schema(description = "포인트 구매내역 총 페이지")
    private int totalPages;
    @Schema(description = "총 포인트 구매내역 개수")
    private int totalElements;
    @Schema(description = "포인트 구매내역 첫 페이지")
    private boolean isFirstPage;
    @Schema(description = "포인트 구매내역 마지막 페이지")
    private boolean isLastPage;
    @Schema(description = "포인트 구매내역 다음 페이지")
    private int nextPage;
    private List<PointPurchaseSearch> pointPurchaseSearchList;
}
