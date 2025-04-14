package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Point;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.mapper.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointRepository {

    @Autowired
    private PointMapper pointMapper;

    public List<PointPurchase> getPointPurchase(int userId,int startIndex, int limitCount, String order) {
        return pointMapper.getPointPurchase(userId, startIndex, limitCount, order);
    }

    public int findAllPointPurchase(int userId) {
        return pointMapper.findAllPointPurchase(userId);
    }

    public int savePointPurchase(int pointId, int userId, String mid, boolean status) {
        return pointMapper.savePurchasePoint(pointId, userId, mid, status);
    }

    public Point getPointByPointId(int pointId) {
        return pointMapper.selectPointByPointId(pointId);
    }


}
