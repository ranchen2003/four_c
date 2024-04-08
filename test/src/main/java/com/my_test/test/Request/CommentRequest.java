package com.my_test.test.Request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentRequest {
    private Long userId; // 用户ID
    private Long imageId; // 图片ID
    private String content;
    private LocalDateTime commentTime;
}
