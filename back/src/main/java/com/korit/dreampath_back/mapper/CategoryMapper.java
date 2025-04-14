package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findAllByBoardId(int boardId);
}
