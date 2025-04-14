package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.admin.ReqAdminPostDto;
import com.korit.dreampath_back.dto.request.admin.ReqAdminUserDto;
import com.korit.dreampath_back.dto.response.admin.RespAdminUserDto;
import com.korit.dreampath_back.dto.response.admin.RespAdminPostListDto;
import com.korit.dreampath_back.dto.response.admin.RespUserListPageDto;
import com.korit.dreampath_back.entity.PostAdmin;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.AdminPostService;
import com.korit.dreampath_back.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@Api(tags = "관리자 API")
@RequestMapping("/api/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminPostService adminPostService;


    @DeleteMapping("/users/{userId}")
    @Operation(summary = "관리자 회원 삭제")
    public ResponseEntity<?> deleteUser(
            @Min(value = 1, message = "회원 ID는 1 이상의 정수입니다.")
            @ApiParam(value = "회원 ID 번호", example = "1", required = true)
            @PathVariable int userId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws NotFoundException {

        return ResponseEntity.ok().body(adminUserService.deleteUser(userId));
    }

    @GetMapping("/posts")
    @Operation(summary = "관리자 게시글 전체 조회")
    public ResponseEntity<?> findAllPosts(@ModelAttribute ReqAdminPostDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {

        int totalPostListCount = adminPostService.findAllAdminPostCount();
        int totalPages = totalPostListCount % dto.getLimitCount() == 0
                ? totalPostListCount / dto.getLimitCount()
                : totalPostListCount / dto.getLimitCount() + 1;

        RespAdminPostListDto respAdminPostListDto = RespAdminPostListDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalPostListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() != totalPages ? dto.getPage() + 1 : dto.getPage())
                .postList(adminPostService.getAdminPostList(dto))
                .build();

        return ResponseEntity.ok().body(respAdminPostListDto);
    }

    @DeleteMapping("/post/{postId}")
    @Operation(summary = "관리자 게시글 삭제")
    public ResponseEntity<?> deletePost(
            @Min(value = 1, message = "게시글 ID는 1 이상의 정수입니다.")
            @ApiParam(value = "게시글 ID 번호", example = "1", required = true)
            @PathVariable int postId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws NotFoundException {

        return ResponseEntity.ok().body(adminPostService.deletePost(postId));
    }

    @GetMapping("/users")
    @Operation(summary = "관리자 회원 전체 조회")
    public ResponseEntity<?> getUserPageList(@ModelAttribute ReqAdminUserDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {

        int totalUserListCount = adminUserService.findAllAdminUser();
        int totalPages = totalUserListCount % dto.getLimitCount() == 0
                ? totalUserListCount / dto.getLimitCount()
                : totalUserListCount / dto.getLimitCount() + 1;

        RespUserListPageDto respUserListPageDto = RespUserListPageDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalUserListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() != totalPages ? dto.getPage() + 1 : dto.getPage())
                .userList(adminUserService.getUserPageList(dto))
                .build();

        return ResponseEntity.ok().body(respUserListPageDto);

    }
}
