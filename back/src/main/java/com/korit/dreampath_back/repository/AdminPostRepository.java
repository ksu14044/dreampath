package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.dto.response.admin.RespAdminPostListDto;
import com.korit.dreampath_back.entity.PostAdmin;
import com.korit.dreampath_back.mapper.AdminPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminPostRepository {

    @Autowired
    private AdminPostMapper adminPostMapper;

    public List<PostAdmin> getAdminPostList(int startIndex, int limitCount, String order){
        return adminPostMapper.findPostAll(startIndex, limitCount, order);
    }

    public int findAllPostCount(){
        return adminPostMapper.findAllPostCount();
    }

    public Optional<Boolean> deleteByPostId(int postId) {
        return adminPostMapper.deleteByPostId(postId) < 1 ? Optional.empty() : Optional.of(true);
    }
}
