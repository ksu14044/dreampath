package com.korit.dreampath_back.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqTicketPurchaseDto {

        @Schema(description = "티켓 구매내역 페이지")
        private int page;
        @Schema(description = "티켓 구매내역 개수 제한")
        private int limitCount;
        @Schema(description = "티켓 구매내역 정렬")
        private String order;
        @Schema(description = "티켓 구매내역 검색")
        private String searchText;

}
