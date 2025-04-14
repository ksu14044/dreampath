package com.korit.dreampath_back.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqAdminUserDto {

    @Schema(description = "관리자 회원 조회 페이지")
    private int page;
    @Schema(description = "관리자 회원 조회 개수 제한 ")
    private int limitCount;
    @Schema(description = "관리자 회원 조회 정렬")
    private String order;
    @Schema(description = "관리자 회원 조회 검색")
    private String searchText;
}
