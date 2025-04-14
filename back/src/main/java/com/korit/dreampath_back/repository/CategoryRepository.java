package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Category;
import com.korit.dreampath_back.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    public List<Category> findAllByBoardId(int boardId) {
        return categoryMapper.findAllByBoardId(boardId);
    }
}
