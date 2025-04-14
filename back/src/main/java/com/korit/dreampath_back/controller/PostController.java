package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostLikeDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.request.post.ReqPostUpdateDto;
import com.korit.dreampath_back.dto.response.post.RespPostListDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.entity.PostDetail;
import com.korit.dreampath_back.entity.PostLike;
import com.korit.dreampath_back.repository.PostRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.PostService;
import com.korit.dreampath_back.service.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SchedulerService SchedulerService;
    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/post")
    @Operation(summary = "게시글 등록")
    public ResponseEntity<String> addPost(
            @AuthenticationPrincipal PrincipalUser principalUser,
            ReqPostCreateDto createDto
    ) {
        return postService.addPost(principalUser.getUser(), createDto)
                ? ResponseEntity.ok().body("등록완료")
                : ResponseEntity.badRequest().body("등록실패");
    }

    @GetMapping("/posts/{boardId}")
    @Operation(summary = "게시글 전체, 다건 조회")
    public ResponseEntity<RespPostListDto> getPostList(
            @PathVariable int boardId,
            @ModelAttribute ReqPostSearchDto searchDto
    ) throws NotFoundException {

        int totalPostListCount = postService.getPostListCountAllBySearchTxt(boardId, searchDto.getSearchTxt());

        int totalPages = totalPostListCount % searchDto.getLimitCount() == 0
                ? totalPostListCount / searchDto.getLimitCount()
                : totalPostListCount / searchDto.getLimitCount() + 1;

        RespPostListDto newRespDto = RespPostListDto.builder()
                .page(searchDto.getPage())
                .limitCount(searchDto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalPostListCount)
                .isFirstPage(searchDto.getPage() == 1)
                .isLastPage(searchDto.getPage() == totalPages)
                .nextPage(searchDto.getPage() != totalPages ? searchDto.getPage() + 1 : 0)
                .postList(postService.getPostList(boardId, searchDto))
                .build();

        return ResponseEntity.ok().body(newRespDto);

    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "게시글 상세 조회")
    public ResponseEntity<PostDetail> getPostDetail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable int postId
    ) throws NotFoundException {
        return ResponseEntity.ok().body(postService.getPostDetail(principalUser, postId));
    }

    @PutMapping("/post/{postId}")
    @Operation(summary = "조회수 갱신")
    public void updatePost(@PathVariable int postId) {
         postRepository.updatePostViewCount(postId);
    }

    @PutMapping("/posts/{postId}")
    @Operation(summary = "게시글 수정")
    public ResponseEntity<String> updatePost(@AuthenticationPrincipal PrincipalUser principalUser, ReqPostUpdateDto updateDto) {
        return postService.updatedPost(principalUser.getUser(), updateDto)
                ? ResponseEntity.ok().body("수정완료")
                : ResponseEntity.badRequest().body("수정실패");
    }

    @DeleteMapping("/posts/{postId}")
    @Operation(summary = "게시글 삭제")
    public ResponseEntity<String> deletePost(@PathVariable int postId, @AuthenticationPrincipal PrincipalUser principalUser) {
        return postService.deletePost(postId, principalUser.getUser())
                ? ResponseEntity.ok().body("삭제완료")
                : ResponseEntity.badRequest().body("삭제실패");
    }

//    @PutMapping("/posts/view/count")
//    @Operation(summary = "게시글 조회수")
//    public void updatePostViewCount(@RequestBody int postId) {
//        postService.updatePostViewCount(postId);
//    }

    @PostMapping("/posts/{postId}/like")
    @Operation(summary = "게시글 좋아요")
    public ResponseEntity<String> likePost(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable int postId) throws Exception {

        return postService.addPostLike(principalUser.getUser(), postId)
                ? ResponseEntity.ok().body("좋아요완료")
                : ResponseEntity.badRequest().body("좋아요실패");
    }

    @DeleteMapping("/posts/{postId}/like")
    @Operation(summary = "게시글 좋아요 취소")
    public ResponseEntity<String> likePostCancel(@AuthenticationPrincipal PrincipalUser principalUser, @PathVariable int postId) {
        return postService.deletePostLike(principalUser.getUser(), postId)
                ? ResponseEntity.ok().body("좋아요취소완료")
                : ResponseEntity.badRequest().body("좋아요취소실패");
    }

    @GetMapping("/posts/{postId}/my/like")
    @Operation(summary = "게시글의 내 좋아요 조회")
    public ResponseEntity<PostLike> selectPostMyLike(@AuthenticationPrincipal PrincipalUser principalUser, @PathVariable int postId) {

        return ResponseEntity.ok().body(postService.findPostMyLike(principalUser.getUser(), postId));
    }


//    @PostMapping("/posts/update/schedule")
//    public ResponseEntity<?> schedule() {
//        schedulerService.updateRecruiting();
//        return ResponseEntity.ok().body(" 성공입니다요");
//    }
}
