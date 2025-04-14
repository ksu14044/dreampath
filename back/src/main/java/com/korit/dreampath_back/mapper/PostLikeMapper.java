package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.PostLike;
import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostLikeMapper {
    //    postId에 해당하는 post에 post_like한 user 찾기
    PostLike findLikeUserByUserIdAndPostId (@Param("userId") int userId, @Param("postId") int postId);

//    좋아요
    int addPostLike (@Param("userId") int userId, @Param("postId") int postId);

//    좋아요 취소
    int deletePostLike (@Param("userId") int userId, @Param("postId") int postId);


}
