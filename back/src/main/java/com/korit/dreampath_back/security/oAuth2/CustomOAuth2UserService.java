package com.korit.dreampath_back.security.oAuth2;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.repository.UserRepository;
import com.korit.dreampath_back.repository.UserRoleRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    @Transactional(rollbackFor = OAuth2AuthenticationException.class)
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String email = null;
        String oauth2Name = null;
        String oauth2Provider = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = getDefaultOAuth2User(userRequest).getAttributes();
        if (oauth2Provider.equalsIgnoreCase("naver")){
            attributes = (Map<String, Object>) attributes.get("response");
            oauth2Name = (String) attributes.get("id");
            email = (String) attributes.get("email");
        }

        if(oauth2Provider.equalsIgnoreCase("google")){
            oauth2Name = (String) attributes.get("sub");
            email = (String) attributes.get("email");
        }
        final String username = oauth2Provider + "_" + oauth2Name;

        final String finalEmail = email;
        final String finalOauth2Name = oauth2Name;
        User user = userRepository
                .findByUsername(username)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .username(username)
                            .nickname(username)
                            .email(finalEmail)
                            .oAuth2Name(finalOauth2Name)
                            .oAuth2Provider(oauth2Provider)
                            .remaining(0)
                            .starPoint(0)
                            .ticketId(0)
                            .remainPoint(0)
                            .accountExpired(1)
                            .accountLocked(1)
                            .credentialsExpired(1)
                            .accountEnabled(1)
                            .createdAt(LocalDateTime.now())
                            .build();


                    User savedUser = userRepository.save(newUser);
                    UserRole userRole = UserRole.builder()
                            .userId(savedUser.getUserId())
                            .roleId(2)
                            .build();
                    userRoleRepository.save(userRole);
                    return userRepository.findByUsername(username).get();
                });

        return PrincipalUser.builder()
                .user(user)
                .name(oauth2Name)
                .attributes(attributes)
                .build();
    }

    private OAuth2User getDefaultOAuth2User(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return defaultOAuth2UserService.loadUser(userRequest);
    }

}