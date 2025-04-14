package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {

    String findUserRoleByUserId(int userId);

    int deleteById(int userId);

    List<UserAdmin> getAdminUserPage(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order
    );

    int findAllUserCount();

}
