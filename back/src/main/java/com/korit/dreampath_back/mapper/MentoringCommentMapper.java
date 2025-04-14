package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.CommentSearch;
import com.korit.dreampath_back.entity.MentoringComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentoringCommentMapper {


    // 후기 등록
    int createComment(MentoringComment mentoringComment);

    int updateComment(MentoringComment mentoringComment
    );


    List<CommentSearch> findCommentByPostId(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("postId") int postId
    );
    int deleteComment(@Param("commentId") int commentId);

    int getCountCommentsByPostId(int postId);
}
