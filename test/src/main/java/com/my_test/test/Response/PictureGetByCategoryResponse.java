package com.my_test.test.Response;

import com.my_test.test.Entities.PictureEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PictureGetByCategoryResponse {
    List<PictureEntity> pictureEntities;
    private boolean success; // 操作是否成功
    private String message; // 消息
}
