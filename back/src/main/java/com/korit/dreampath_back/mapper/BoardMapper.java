package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    Board selectBoardIdByBoardName(@Param("boardName") String boardName);
    List<Board> findAll();
}
