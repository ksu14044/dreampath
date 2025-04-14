package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.point.ReqPointDto;
import com.korit.dreampath_back.dto.request.point.ReqPointPurchaseDto;
import com.korit.dreampath_back.entity.Point;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.repository.PointRepository;
import com.korit.dreampath_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PointPurchaseSearch> getPointPurchase(int userId, ReqPointPurchaseDto dto) {
        int startIndex = (dto.getPage() - 1 ) * dto.getLimitCount();
        List<PointPurchase> pointPurchaseList = pointRepository.getPointPurchase(userId,startIndex,dto.getLimitCount(), dto.getOrder());
        List<PointPurchaseSearch> pointPurchaseSearches = new ArrayList<>();
        for(PointPurchase pointPurchase : pointPurchaseList){
            PointPurchaseSearch pointPurchaseSearch = PointPurchaseSearch.builder()
                    .pointName(pointPurchase.getPoint().getPointName())
                    .pointPrice(pointPurchase.getPoint().getPointPrice())
                    .createdAt(pointPurchase.getCreatedAt())
                    .build();
            pointPurchaseSearches.add(pointPurchaseSearch);
        }
        return pointPurchaseSearches;
    }

    public int findAllPointPurchase(int userId) {
        return pointRepository.findAllPointPurchase(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public int savePointPurchase(User user, ReqPointDto dto) {
        int save = pointRepository.savePointPurchase(dto.getPointId(), user.getUserId(), dto.getMid(), dto.isStatus());

        if(save > 0) {
            Point foundPoint = pointRepository.getPointByPointId(dto.getPointId());
//            user > remain_point 업데이트
            userRepository.updatePointRemainingCount(user.getUserId(), foundPoint.getPointAmount());
        }

        return save;
    }

}
