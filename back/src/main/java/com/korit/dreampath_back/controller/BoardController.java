package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.entity.Board;
import com.korit.dreampath_back.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private PostService postService;

    @GetMapping("/boards")
    @Operation(summary = "전체 게시판 조회")
    public ResponseEntity<List<Board>> getBoards() {
        return ResponseEntity.ok().body(postService.findAllBoard());
    }
}
