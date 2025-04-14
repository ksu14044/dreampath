package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.MentoringRegister;
import com.korit.dreampath_back.entity.MyApplySearch;
import com.korit.dreampath_back.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplyRepository {

    @Autowired
    private ApplyMapper applyMapper;

    public Optional<List<MentoringRegister>> getMentoringRegisterList(int userId, int postId) {

        return Optional.ofNullable(applyMapper.getMentoringRegisterList(userId, postId));
    }

    public void insertMentoringRegister(MentoringRegister mentoringRegister) {
        applyMapper.insertMentoringRegister(mentoringRegister);
    }

    public List<MyApplySearch> getMyApplySearchList(int userId, int startIndex, int limitCount, String order, String searchText) {
        return applyMapper.getMyApplySearchList(userId, startIndex, limitCount, searchText, order);
    }

    public int getMyApplyListCountBySearchText(int userId, String searchText) {
        return applyMapper.findMyApplySearchListBySearchText(userId, searchText);
    }

}
