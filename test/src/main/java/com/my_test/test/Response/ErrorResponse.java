package com.my_test.test.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Integer statusCode; // HTTP状态码
    private String message; // 错误消息
    private Long timestamp; // 发生错误的时间戳
}
