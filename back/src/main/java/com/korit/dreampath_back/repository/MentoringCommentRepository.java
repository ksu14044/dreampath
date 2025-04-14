package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.CommentSearch;
import com.korit.dreampath_back.entity.MentoringComment;
import com.korit.dreampath_back.mapper.MentoringCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MentoringCommentRepository {

    @Autowired
    private MentoringCommentMapper mentoringCommentMapper;

    // 댓글 조회
    public List<CommentSearch> getCommentPageWithNickname(int startIndex, int limitCount, int postId) {
        return mentoringCommentMapper.findCommentByPostId(startIndex, limitCount, postId );
    }

    public int getCountsByPostId(int postId){
        return mentoringCommentMapper.getCountCommentsByPostId(postId);
    }


    public int addComment(MentoringComment mentoringComment) {

        return mentoringCommentMapper.createComment(mentoringComment);
    }

    // 수정
    public int updateComment(MentoringComment mentoringComment) {

        return mentoringCommentMapper.updateComment(mentoringComment); }

    public List<CommentSearch> findCommentByPostId(int startIndex, int limitCount, int postId) {
        return mentoringCommentMapper.findCommentByPostId(startIndex, limitCount, postId);
    }

    // 삭제
    public int deleteComment(int commentId) { return mentoringCommentMapper.deleteComment(commentId); }


}
