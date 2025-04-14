package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.ReqLoginDto;
import com.korit.dreampath_back.dto.request.ReqSignupDto;
import com.korit.dreampath_back.dto.response.RespTokenDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody ReqSignupDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {
        if(principalUser != null) {
            return ResponseEntity.badRequest().body("로그아웃 후 이용해주세요.");
        }
        return ResponseEntity.ok().body(userService.save(dto));
    }


    @Operation(summary = "로그인", description = "로그인 설명")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqLoginDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {
        if(principalUser != null) {
            return ResponseEntity.badRequest().body("이미 로그인되었습니다.");
        }
        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("JWT")
                .name("AccessToken")
                .token(userService.login(dto))
                .build();

        return ResponseEntity.ok().body(respTokenDto);
    }
}
