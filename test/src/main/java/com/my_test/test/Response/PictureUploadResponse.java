package com.my_test.test.Response;

import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PictureUploadResponse {
    private UserEntity userEntity;
    private PictureEntity pictureEntity;
    private boolean success; // 操作是否成功
    private String message; // 消息
}
