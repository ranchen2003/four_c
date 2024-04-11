package com.my_test.test.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PictureGetByCategoryRequest {
    private String pictureCategory;
}
