package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.dto.request.ReqMyApplySearchDto;
import com.korit.dreampath_back.dto.response.RespMyApplyList;
import com.korit.dreampath_back.entity.MentoringRegister;
import com.korit.dreampath_back.exception.DuplicatedValueException;
import com.korit.dreampath_back.repository.ApplyRepository;
import com.korit.dreampath_back.security.jwt.JwtUtil;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class ApplyService {
    @Autowired
    private JwtUtil jwtUtil;

    private final String FROM_EMAIL = "rlatldnr1234@gmail.com";

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired
    private ApplyRepository applyRepository;

    public void sendApplyMail(ReqApplyEmailDto reqApplyEmailDto, PrincipalUser principalUser) throws MessagingException {
        String nickname = principalUser.getUser().getNickname();

        String email = principalUser.getUser().getEmail();


        String toEmail = reqApplyEmailDto.getEmail();

        final String SUBJECT = "멘토링 신청 이메일입니다.";

        String content = String.format("""
                <html lang="ko">
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body>
                        <div style="display: flex; flex-direction: column; align-items: center; ">
                            <h1>멘토링 신청 승인 확인</h1>
                            <p>%s님께서 귀하의 멘토링 수업에 참가하고자 합니다.</p>
                            <p>멘토링 신청자 이메일 주소 : %s</p>
                            <p>멘토링을 진행하시려면 위의 주소로 연락 바랍니다.</p>
                        </div>
                    </body>
                    </html>
                """, nickname, email);

            applyRepository.insertMentoringRegister(MentoringRegister.builder().userId(principalUser.getUser().getUserId()).postId(reqApplyEmailDto.getPostId()).build());
            sendMail(toEmail, SUBJECT, content);
    }

    @Async
    public void sendMail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(FROM_EMAIL);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessage.setText(content, StandardCharsets.UTF_8.name(), "html");
        mailSender.send(mimeMessage);
    }

    public boolean isApplied (PrincipalUser principalUser, int postId) {
        return applyRepository.getMentoringRegisterList(principalUser.getUser().getUserId(), postId).get().isEmpty();
    }

    public RespMyApplyList getMyApplyList(PrincipalUser principalUser, ReqMyApplySearchDto dto) {
        int userId = principalUser.getUser().getUserId();

        int totalMyApplyListCount = applyRepository.getMyApplyListCountBySearchText(userId, dto.getSearchText());
        int totalPages = totalMyApplyListCount % dto.getLimitCount() == 0
                ? totalMyApplyListCount / dto.getLimitCount()
                : totalMyApplyListCount / dto.getLimitCount() + 1;
        int startIndex = (dto.getPage()-1) * dto.getLimitCount();
        RespMyApplyList respDto = RespMyApplyList.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalMyApplyListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() == totalPages ? dto.getPage() : dto.getPage() + 1)
                .myMentoringSearchList(applyRepository.getMyApplySearchList(userId, startIndex, dto.getLimitCount(), dto.getOrder(), dto.getSearchText()))
                .build();

        return respDto;
    }
}
