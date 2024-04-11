package com.my_test.test.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import java.util.List;

@Data
@AllArgsConstructor
public class UserGetInfoResponse {
    private boolean success; // 操作是否成功
    private String message; // 消息
    private UserEntity user;
    private List<CommentEntity> comments; // 用户的评论列表，考虑创建一个CommentDTO
    private List<PictureEntity> pictures; // 用户上传的图片列表，考虑创建一个PictureDTO
}
