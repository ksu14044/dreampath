package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole userRole);
}
