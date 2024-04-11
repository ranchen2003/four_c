package com.my_test.test.Response;

import com.my_test.test.Entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDeleteResponse {
    private boolean success; // 操作是否成功
    private String message; // 消息
}
