package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.admin.ReqAdminUserDto;
import com.korit.dreampath_back.dto.response.admin.RespAdminUserDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserAdmin;
import com.korit.dreampath_back.entity.UserSearch;
import com.korit.dreampath_back.mapper.AdminUserMapper;
import com.korit.dreampath_back.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private AdminUserMapper adminUserMapper;

    public String getUserRoleName(int userId) {
        String userRoleName = adminUserRepository.findUserRoleName(userId);
        return userRoleName;
    }


    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(int userId) throws NotFoundException {
        return adminUserRepository.deleteById(userId)
                .orElseThrow(() -> new NotFoundException("해당 회원 ID가 존재하지 않습니다."));
    }

    public List<UserAdmin> getUserPageList(ReqAdminUserDto dto) {

        int startIndex = (dto.getPage() - 1) * dto.getLimitCount();

        List<UserAdmin> userList = adminUserRepository.getUserPageList( startIndex, dto.getLimitCount(), dto.getOrder());

        int totalUserCount = adminUserMapper.findAllUserCount();

        for (UserAdmin userAdmin : userList) {
            userAdmin.setTotalUser(totalUserCount);
        }

        return userList;
    }

    public int findAllAdminUser() {
        return adminUserRepository.findAllUserCount();
    }
}
