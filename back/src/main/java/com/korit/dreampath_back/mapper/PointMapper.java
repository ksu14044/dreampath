package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Point;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointMapper {
    List<PointPurchase> getPointPurchase(
            int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order);

    int findAllPointPurchase(int userId);

//    구매 내역 저장
    int savePurchasePoint(
            @Param("pointId") int pointId,
            @Param("userId") int userId,
            @Param("mid") String mid,
            @Param("status") boolean status);

//    포인트 테이블에서 포인트 아이디로 포인트 정보 조회
    Point selectPointByPointId(int pointId);
}
