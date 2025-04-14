package com.korit.dreampath_back.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "게시글 검색 요청 DTO")
public class ReqPostSearchDto {
    @Schema(description = "페이징", required = true)
    private int page;
    @Schema(description = "게시글 갯수", required = true)
    private int limitCount;
    @Schema(description = "게시글 정렬",
            defaultValue = "desc",
            allowableValues = {
                    "desc: 최신순",
                    "asc: 오래된순",
                    "startDesc: 평점높은순",
                    "commentDesc: 후기많은순",
                    "likeDesc: 좋아요많은순"
            },
            required = true
    )
    private String order;
    @Schema(description = "게시글 상태",
            defaultValue = "",
            allowableValues = {
                    "recruiting: 모집중",
                    "closedRecruitment: 모집마감",
            },
            required = true
    )
    private String status;

    @Schema(description = "게시글 카테고리",
            defaultValue = "",
            allowableValues = {
                    "back: 백엔드" ,
                     "front: 프론트엔드",
                    "security: '정보보안",
                    "full: 풀스택",
            },
            required = true
    )
    private String category;

    @Schema(description = "게시글 검색 텍스트")
    private String searchTxt;

}
