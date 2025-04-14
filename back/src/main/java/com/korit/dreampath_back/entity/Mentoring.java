package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class Mentoring {

    private int starPoint;
    private String content;
    private LocalDateTime createdAt;

    private String nickName;
}
