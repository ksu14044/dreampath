package com.korit.dreampath_back.dto.response.admin;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserAdmin;
import com.korit.dreampath_back.entity.UserSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespUserListPageDto {

    @Schema(description = "관리자 회원 조회 페이지")
    private int page;
    @Schema(description = "관리자 회원 조회 개수 제한")
    private int limitCount;
    @Schema(description = "관리자 회원 조회 총 페이지")
    private int totalPages;
    @Schema(description = "총 관리자 회원 조회 개수")
    private int totalElements;
    @Schema(description = "관리자 회원 조회 첫 페이지")
    private boolean isFirstPage;
    @Schema(description = "관리자 회원 조회 마지막 페이지")
    private boolean isLastPage;

    @Schema(description = "관리자 회원 조회 다음 페이지")
    private int nextPage;
    private List<UserAdmin> userList;
}
