package com.korit.dreampath_back.service;

import com.korit.dreampath_back.entity.Category;
import com.korit.dreampath_back.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository mentoringCategoryRepository;

    public List<Category> findAllCategories(int boardId) {
        return mentoringCategoryRepository.findAllByBoardId(boardId);
    }
}
