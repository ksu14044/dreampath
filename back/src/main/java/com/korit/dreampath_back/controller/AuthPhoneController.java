package com.korit.dreampath_back.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.model.StorageType;
import net.nurigo.sdk.message.request.MessageListRequest;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MessageListResponse;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@RestController
public class AuthPhoneController {

    @Value(value = "${coolsms.api.from}")
    private String from;

    final DefaultMessageService messageService;

    public AuthPhoneController(
            @Value(value = "${coolsms.api.key}") String apiKey,
            @Value(value = "${coolsms.api.secret}") String apiSecret
    ) {

        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    /**
     * 단일 메시지 발송 예제
     */
    @PostMapping("/api/send-one")
    @Operation(summary = "인증메일 전송")
    public String sendOne(@RequestBody Map<String, String> requestBodyMap) {
        Message message = new Message();
        String phoneNumber = requestBodyMap.get("phoneNumber");
        Random random = new Random();
        String code = String.valueOf(random.nextInt(1000000));
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01098739389");
        message.setTo(phoneNumber);
        message.setText("인증번호는 " + code + "입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));


        return code;
    }
}