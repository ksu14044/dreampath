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
public class MentoringRegister {
    private int mentoringRegisterId;
    private int userId;
    private int postId;
    LocalDateTime applyAt;
}
