package com.my_test.test.Response;

import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String accountName;
    private String nickname;
    private String avatarUrl;
    private Integer likesCount;
    private Integer followersCount;
    private Instant registrationTime;
    private List<PictureResponse> pictures;
}
