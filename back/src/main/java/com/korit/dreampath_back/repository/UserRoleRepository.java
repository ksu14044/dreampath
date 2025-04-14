package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class UserRoleRepository {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRole save (UserRole userRole) {
        userRoleMapper.insert(userRole);
        return userRole;
    }

}
