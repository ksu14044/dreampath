package com.korit.dreampath_back.security.oAuth2;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.security.jwt.JwtUtil;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value(value = "${react.server.protocol}")
    private String protocol;
    @Value(value = "${react.server.host}")
    private String host;
    @Value(value = "${react.server.port}")
    private int port;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        User user = principalUser.getUser();
        Date expires = new Date(new Date().getTime() + (10001 * 60 * 60 * 7));
        String accessToken = jwtUtil.generateToken(user.getUsername(), Integer.toString(user.getUserId()), expires);
//
//
        //url 주소 설정, 이 주소로 리다이렉트
        response.sendRedirect(String.format("%s://%s/auth/login/oauth2?accessToken=%s", protocol, host, accessToken));
    }

}