package com.korit.dreampath_back.dto.response.admin;

import com.korit.dreampath_back.entity.PostAdmin;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserAdmin;
import com.korit.dreampath_back.entity.UserSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespAdminPostListDto {

    @Schema(description = "관리자 게시글 페이지")
    private int page;
    @Schema(description = "관리자 게시글 개수 제한")
    private int limitCount;
    @Schema(description = "관리자 게시글 총 페이지")
    private int totalPages;
    @Schema(description = "관리자 총 게시글 개수")
    private int totalElements;
    @Schema(description = "관리자 게시글 첫 페이지")
    private boolean isFirstPage;
    @Schema(description = "관리자 게시글 마지막 페이지")
    private boolean isLastPage;

    @Schema(description = "관리자 게시글 다음 페이지")
    private int nextPage;

    private List<PostAdmin> postList;
}
