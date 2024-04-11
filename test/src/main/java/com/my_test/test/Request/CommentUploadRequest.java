package com.my_test.test.Request;

import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentUploadRequest {
    private UserEntity userEntity;
    private PictureEntity pictureEntity;
    private String comment;
}
