package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.dto.response.admin.RespAdminPostListDto;
import com.korit.dreampath_back.entity.PostAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPostMapper {

    List<PostAdmin> findPostAll(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order

    );

    int findAllPostCount();

    int deleteByPostId(int postId);
}
