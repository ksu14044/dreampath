package com.korit.dreampath_back.mapper;


import com.korit.dreampath_back.entity.MyMentoringSearch;
import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    int deleteUserById(int userId);

    User selectById(int userId);
    User selectByUsername(String username);
    User selectByPassword(String password);
    User selectByNickname(String nickname);
    User selectByRolename(String roleName);
    User selectByEmail(String email);
    User selectByAccountNumber(String accountNumber);
    User selectByPhoneNumber(String phoneNumber);
    User selectByAddress(String address);
    User selectByProfileImg(String profileImg);
    User selectByTicketId(String ticketId);
    User selectByStarPoint(String starPoint);
    User selectByRoleList(String roleList);


    int updateProfileImgById(
            @Param("userId") int userId,
            @Param("profileImg") String profileImg);


    int updateNicknameById(
            @Param("userId") int userId,
            @Param("nickname") String nickname);

    int updatePasswordById(
            @Param("userId") int userId,
            @Param("password") String password);

    int updateEmailById(
            @Param("userId") int userId,
            @Param("email") String email);

    int updateRemainPoint(
            @Param("userId") int userId,
            @Param("price") int price
    );

    int updateRemaining(
            @Param("userId") int userId,
            @Param("entryCount") int entryCount
    );

    int getMyMentoringListCountBySearchText(int userId, String searchText);

    List<MyMentoringSearch> findAllMyMentoring(
            @Param("userId") int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("searchText") String searchText,
            @Param("order") String order
    );

    int updateRemainingCount(
            @Param("userId") int userId
    );
    int updatePointRemainingCount(
            @Param("userId") int userId,
            @Param("amount") int amount
    );

    boolean updatePhoneNumber(
            @Param("userId") int userId,
            @Param("phoneNumber") String phoneNumber
    );

    double getUserStarPointAvg(int userId);

    int updateUserStarPointAvgByPostId(int userId, double userStarPoint);

    int getUserIdByPostId(int postId);

}
