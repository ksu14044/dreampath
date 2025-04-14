package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyApplySearch {
    String title;
    String mento;
    LocalDate createdAt;
    int postId;
}
