package com.my_test.test.Response;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PictureGetInfoResponse {
    private UserEntity userEntity;
    private PictureEntity pictureEntity;
    List<CommentEntity> commentEntityList;
    private boolean success; // 操作是否成功
    private String message; // 消息
}
