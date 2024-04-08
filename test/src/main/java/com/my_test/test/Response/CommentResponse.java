package com.my_test.test.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private Long userId; // 用户ID
    private Long imageId; // 图片ID
    private String content;
    private LocalDateTime commentTime;
}
