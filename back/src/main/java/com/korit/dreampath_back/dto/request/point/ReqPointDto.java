package com.korit.dreampath_back.dto.request.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqPointDto {

    @Schema(description = "포인트 고유 ID")
    private int pointId;
    @Schema(description = "가맹점 ID")
    private String mid;
    @Schema(description = "상태")
    private boolean status;
}
