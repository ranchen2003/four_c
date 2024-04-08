package com.my_test.test.Response;

import lombok.Data;
import java.time.Instant;

@Data
public class PictureResponse {
    private Long imageId;
    private Integer likesCount;
    private String url;
    private String category;
    private String creationIntent;
    private Instant uploadTime;
    private Long userId; // 用户ID用于展示图片所属的用户
}
