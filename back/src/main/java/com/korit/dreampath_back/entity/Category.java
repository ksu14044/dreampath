package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryNameKor;
    private int boardId;
    private String type;
}
