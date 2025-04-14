package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointPurchase {
    private int pointPurchaseId;
    private int pointId;
    private int userId;
    private String mid;
    private String status;
    private LocalDateTime createdAt;

    private Point point;
    private User user;
}
