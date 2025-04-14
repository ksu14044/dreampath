package com.korit.dreampath_back.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentSearch {
    private int commentId;
    private int starPoint;
    private String content;
    private int userId;
    private String nickname;
    private String profileImg;
    private LocalDateTime createdAt;

}
