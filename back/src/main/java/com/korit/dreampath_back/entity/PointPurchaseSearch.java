package com.korit.dreampath_back.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PointPurchaseSearch {
    String pointName;
    int pointPrice;
    LocalDateTime createdAt;
}
