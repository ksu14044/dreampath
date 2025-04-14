package com.korit.dreampath_back.dto.response.comment;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMentoringCommentDto {

    @Schema(description = "멘토링 후기 ID")
    private int commentId;
    @Schema(description = "멘토링 후기")
    private String content;

    private int starPoint;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "멘토링 후기 수정 날짜")
    private LocalDateTime updateAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "멘토링 후기 생성 날짜")
    private LocalDateTime createdAt;

    @Schema(description = "멘토링 후기 닉네임")
    private String nickname;

}
