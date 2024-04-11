package com.my_test.test.Request;

import com.my_test.test.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PictureUploadRequest {
    private UserEntity userEntity;
    private MultipartFile multipartFile;
    private String creationIntent;
    private Instant uploadTime;
    private String category;
}
