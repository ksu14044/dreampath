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
public class MentoringComment {

    private int postId;
    private int userId;

    private int commentId ;
    private String content;

    private int starPoint;

    private LocalDateTime updateAt;
    private LocalDateTime createdAt;

    private String nickname;

}
