package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Board {

    private int boardId;
    private String boardName;
    private String boardNameKor;
}
