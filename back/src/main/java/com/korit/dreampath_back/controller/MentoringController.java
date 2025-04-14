package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.entity.Category;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.CategoryService;
import com.korit.dreampath_back.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentoring")
public class MentoringController {

    @Autowired
    private CategoryService mentoringService;

    @Autowired
    private PostService postService;

    @PutMapping("/status")
    @Operation(summary = "모집중 / 모집마감 상태 변경")
    public ResponseEntity<?> setStatus(@RequestParam int postId, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok().body(postService.updatePostStatus(postId, principalUser.getUser()));
    }

}
