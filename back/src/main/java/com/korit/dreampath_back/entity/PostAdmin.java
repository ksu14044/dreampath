package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAdmin {
    private int postId;
    private String title;
    private String nickname;
    private String boardName;
    private LocalDateTime createdAt;
    private int commentCount;
    private int viewCount;
}
