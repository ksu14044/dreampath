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
public class PostListPage {
    private int postId;
    private int boardId;
    private int userId;
    private int categoryId;
    private String categoryName;
    private String categoryNameKor;
    private String title;
    private String content;
    private int starPoint;
    private String status;
    private LocalDateTime createdAt;

    private int likeCount;
    private int commentCount;
    private int viewCount;

    private User user;
}
