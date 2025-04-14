package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.point.ReqPointDto;
import com.korit.dreampath_back.dto.request.point.ReqPointPurchaseDto;
import com.korit.dreampath_back.dto.response.RespPointPurchaseListDto;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {
    @Autowired
    private PointService pointService;
    @GetMapping("/purchase")
    @Operation(summary = "내 포인트 충전 내역 조회")
    public ResponseEntity<?> getPointPurchase(@ModelAttribute ReqPointPurchaseDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();

        int totalPointPurchaseListCount = pointService.findAllPointPurchase(userId);
        int totalPages = totalPointPurchaseListCount % dto.getLimitCount() == 0
                ? totalPointPurchaseListCount / dto.getLimitCount()
                : totalPointPurchaseListCount / dto.getLimitCount() + 1;

        RespPointPurchaseListDto pointPurchaseListDto = RespPointPurchaseListDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalPointPurchaseListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() != totalPages ? dto.getPage() + 1 : dto.getPage())
                .pointPurchaseSearchList(pointService.getPointPurchase(userId, dto))
                .build();

        return ResponseEntity.ok().body(pointPurchaseListDto);
    }


    @PostMapping("/purchase")
    @Operation(summary = "포인트 충전 내역 저장")
    public ResponseEntity<String> savePointPurchase(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody ReqPointDto dto) {
        int save = pointService.savePointPurchase(principalUser.getUser(), dto);
        return save > 0 ? ResponseEntity.ok().body("저장되었습니다.") : ResponseEntity.badRequest().body("실패되었습니다.");
    }


}
