package com.my_test.test.Request;

import lombok.Data;
import java.time.Instant;

@Data
public class UserRequest {
    private String accountName;
    private String nickname;
    private String password; // 注意在实际应用中应当处理敏感信息
    private String avatarUrl;
    private Integer likesCount;
    private Integer followersCount;
    private Instant registrationTime;
}
