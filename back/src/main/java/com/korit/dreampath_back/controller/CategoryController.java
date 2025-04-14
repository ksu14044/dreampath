package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.entity.Category;
import com.korit.dreampath_back.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "게시판 ID별 카테고리 전체 조회")
    public ResponseEntity<List<Category>> getCategory(@RequestParam int boardId) {
        return ResponseEntity.ok().body(categoryService.findAllCategories(boardId));
    }
}
