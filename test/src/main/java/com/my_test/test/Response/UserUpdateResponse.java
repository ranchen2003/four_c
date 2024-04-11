package com.my_test.test.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateResponse {
    private boolean success; // 操作是否成功
    private String message; // 消息
}

