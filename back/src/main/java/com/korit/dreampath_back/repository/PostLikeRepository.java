package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.PostLike;
import com.korit.dreampath_back.mapper.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostLikeRepository {

    @Autowired
    private PostLikeMapper postLikeMapper;

    public PostLike findPostLikeUserByUserId(int userId, int postId) {
        return postLikeMapper.findLikeUserByUserIdAndPostId(userId, postId);
    }

    public int addPostLike(int userId, int postId) {
        return postLikeMapper.addPostLike(userId, postId);
    }

    public int deletePostLike(int userId, int postId) {
        return postLikeMapper.deletePostLike(userId, postId);
    }

}
