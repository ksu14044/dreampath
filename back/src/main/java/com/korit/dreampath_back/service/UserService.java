package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.dto.request.ReqLoginDto;
import com.korit.dreampath_back.dto.request.ReqMyMentoringSearchDto;
import com.korit.dreampath_back.dto.request.ReqSignupDto;
import com.korit.dreampath_back.dto.response.RespMyMentoringList;
import com.korit.dreampath_back.dto.response.User.RespUserDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.exception.DuplicatedValueException;
import com.korit.dreampath_back.exception.FieldError;
import com.korit.dreampath_back.repository.UserRepository;
import com.korit.dreampath_back.repository.UserRoleRepository;
import com.korit.dreampath_back.security.jwt.JwtUtil;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private FileService fileService;

    public RespUserDto getUserInfo(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return RespUserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .roleName(user.getRoleName())
                .starPoint(user.getStarPoint())
                .remaining(user.getRemaining())
                .remainPoint(user.getRemainPoint())
                .profileImg(user.getProfileImg() == null ? "default.png" : user.getProfileImg())
                .createdAt(user.getCreatedAt())
                .build();

    }

    public boolean duplicatedUsername(String username) {
        return userRepository.findByUsername(username).isPresent(); }

    public boolean duplicatedNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public User save( ReqSignupDto dto){
        if(duplicatedUsername(dto.getUsername())){
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("username")
                    .message("이미 존재하는 사용자입니다.")
                    .build()));
        }
        if(duplicatedNickname(dto.getNickname())){
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("nickname")
                    .message("이미 존재하는 닉네임입니다.")
                    .build()));
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
        userRepository.save(user);

        UserRole userRole = UserRole.builder()
                .userId(user.getUserId())
                .roleId(dto.getRoleId())
                .build();
        userRoleRepository.save(userRole);
        return user;
    }

    public String login(ReqLoginDto dto) {
        User user = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인하세요."));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        Date expires = new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 7));

        return jwtUtil.generateToken(
                user.getUsername(),
                Integer.toString(user.getUserId()),
                expires);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProfileImg(User user, MultipartFile file) {
        final String PROFILE_IMG_FILE_PATH = "/upload/user/profile";
        String savedFileName = fileService.saveFile(PROFILE_IMG_FILE_PATH, file);

        userRepository.updateProfileImg(user.getUserId(), savedFileName);

        if (user.getProfileImg() == null) {return;}
        fileService.deleteFile(PROFILE_IMG_FILE_PATH + "/" + user.getProfileImg());


    }

    @Transactional(rollbackFor = Exception.class)
    public void updateNickname(User user, String nickname) {
        userRepository.updateNickname(user.getUserId(), nickname);


    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        userRepository.updatePassword(user.getUserId(), encodedPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(User user, String email) {
        userRepository.updateEmail(user.getUserId(), email);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(User user) {
        userRepository.deleteUser(user.getUserId());
    }


    public RespMyMentoringList getMyMentoring(PrincipalUser principalUser, ReqMyMentoringSearchDto dto) {
        int userId = principalUser.getUser().getUserId();

        int totalMyMentoringListCount = userRepository.findMyMentoringCountBySearchText(userId, dto.getSearchText());
        int totalPages = totalMyMentoringListCount % dto.getLimitCount() == 0
                ? totalMyMentoringListCount / dto.getLimitCount()
                : totalMyMentoringListCount / dto.getLimitCount() + 1;

        int startIndex = (dto.getPage()-1) * dto.getLimitCount();

        RespMyMentoringList respDto = RespMyMentoringList.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalMyMentoringListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() == totalPages ? totalPages : dto.getPage() + 1)
                .myMentoringSearchList(userRepository.findAllMentoring(userId, startIndex, dto.getLimitCount(), dto.getOrder(), dto.getSearchText()))
                .build();

        return respDto;
    }

    public String updatePhoneNumber(PrincipalUser principalUser, String phoneNumber) {
        String message = "";
        if(userRepository.updatePhoneNumber(principalUser.getUser().getUserId(), phoneNumber)) {
            message = "변경이 완료되었습니다.";
        } else {
            message = "변경에 실패했습니다";
        }
        return message;
    }



}
