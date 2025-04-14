package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyMentoringSearch {
    private String status;
    private String title;
    private LocalDate createdAt;
    private int commentCount;
    private int likeCount;
    private int viewCount;
    private int postId;
}
