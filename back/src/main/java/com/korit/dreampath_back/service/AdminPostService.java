package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.admin.ReqAdminPostDto;
import com.korit.dreampath_back.dto.request.admin.ReqAdminUserDto;
import com.korit.dreampath_back.dto.response.admin.RespAdminPostListDto;
import com.korit.dreampath_back.entity.PostAdmin;
import com.korit.dreampath_back.repository.AdminPostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminPostService {

    @Autowired
    private AdminPostRepository adminPostRepository;


    public List<PostAdmin> getAdminPostList(ReqAdminPostDto dto) {

        int startIndex = (dto.getPage() - 1) * dto.getLimitCount();

        List<PostAdmin> postList = adminPostRepository.getAdminPostList(startIndex, dto.getLimitCount(), dto.getOrder());

        return postList;
    }


    public int findAllAdminPostCount() {
        return adminPostRepository.findAllPostCount();
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deletePost(int postId) throws NotFoundException {
        return adminPostRepository.deleteByPostId(postId)
                .orElseThrow(() -> new NotFoundException("해당 게시글 ID가 존재하지 않습니다."));
    }
}
