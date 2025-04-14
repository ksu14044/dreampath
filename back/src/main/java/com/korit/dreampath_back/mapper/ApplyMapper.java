package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.MentoringRegister;
import com.korit.dreampath_back.entity.MyApplySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyMapper {

    List<MentoringRegister> getMentoringRegisterList(int userId, int postId);

    int insertMentoringRegister(MentoringRegister mentoringRegister);

    List<MyApplySearch> getMyApplySearchList(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("searchText") String searchText,
            @Param("order") String order
    );

    int findMyApplySearchListBySearchText(
            int userId,
            String searchText
    );
}
