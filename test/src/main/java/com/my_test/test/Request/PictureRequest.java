package com.my_test.test.Request;

import lombok.Data;
import java.time.Instant;

@Data
public class PictureRequest {
    private Integer likesCount;
    private String url;
    private String category;
    private String creationIntent;
    private Instant uploadTime;
    private Long userId; // 用户ID用于关联图片与用户
}
