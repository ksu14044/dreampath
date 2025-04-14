package com.korit.dreampath_back.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
@Schema(description = "게시글 등록 요청 DTO")
public class ReqPostCreateDto {
    @Schema(description = "게시판 타입 아이디", required = true)
    private int boardId;
    @Schema(description = "멘토링 카데고리 아이디")
    private int categoryId;
    @Schema(description = "게시글 제목", required = true)
    private String title;
    @Schema(description = "게시글 내용", required = true)
    private String content;
    @Schema(description = "멘토링 만남 장소 주소")
    private String mentoringAddress;
    @Schema(description = "멘토링 시작 날짜")
    private LocalDate startDate;
    @Schema(description = "멘토링 종료 날짜")
    private LocalDate endDate;
    @Schema(description = "게시글 첨부 파일")
    private MultipartFile file;
}
