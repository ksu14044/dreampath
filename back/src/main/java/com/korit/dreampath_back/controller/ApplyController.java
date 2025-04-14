package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.dto.request.ReqMyApplySearchDto;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.ApplyService;
import com.korit.dreampath_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentoring")
public class ApplyController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply")
    @Operation(summary = "신청 메일 전송")
    @Async
    public void sendApplyMail(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody ReqApplyEmailDto reqApplyEmailDto) throws Exception {
        if (applyService.isApplied(principalUser, reqApplyEmailDto.getPostId())) {
            applyService.sendApplyMail(reqApplyEmailDto, principalUser);
        } else {

        }
    }

    @GetMapping("/me/applyList")
    @Operation(summary = "내가 신청한 멘토링 내역")
    public ResponseEntity<?> getMyApplyList(@AuthenticationPrincipal PrincipalUser principalUser, @ModelAttribute ReqMyApplySearchDto dto) {
        return ResponseEntity.ok().body(applyService.getMyApplyList(principalUser, dto));
    }

}
