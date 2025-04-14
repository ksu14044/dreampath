package com.korit.dreampath_back.controller;


import com.korit.dreampath_back.dto.request.ReqTicketPurchaseDto;
import com.korit.dreampath_back.dto.response.RespTicketPurchaseListDto;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/purchase")
    @Operation (summary="내 이용권 구매 내역")
    public ResponseEntity<RespTicketPurchaseListDto> findAllByUserId(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute ReqTicketPurchaseDto dto
    ) {
       return ResponseEntity.ok().body(ticketService.getPointPurchase(principalUser, dto));
    }

    @PutMapping("/renewal/remaining")
    @Operation(summary = "이용권 구매하기")
    public ResponseEntity<?> updateRemainingEntryCount(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody Map<String, Integer> ticketId) {
        return ResponseEntity.ok().body(ticketService.updateRemainingEntryCount(principalUser, ticketId.get("ticketId")));
    }

}


