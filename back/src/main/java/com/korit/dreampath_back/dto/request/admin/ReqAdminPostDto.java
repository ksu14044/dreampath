package com.korit.dreampath_back.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqAdminPostDto {

    @Schema(description = "관리자 게시글 페이지")
    private int page;
    @Schema(description = "관리자 게시글 개수 제한 ")
    private int limitCount;
    @Schema(description = "관리자 게시글 정렬")
    private String order;
    @Schema(description = "관리자 게시글 검색")
    private String searchText;
}
