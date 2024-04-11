package com.my_test.test.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    private String username; // 用户名
    private String password; // 密码
}

