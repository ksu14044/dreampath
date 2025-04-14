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
public class Comment {

    private int commentId;
    private int userId;
    private int postId;
    private String content;
    private int starPoint;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
