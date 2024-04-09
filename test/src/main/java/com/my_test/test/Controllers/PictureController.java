package com.my_test.test.Controllers;

import com.my_test.test.Entities.UserEntity;
import com.my_test.test.Repositories.UserRepository;
import com.my_test.test.Request.PictureGetByCategoryRequest;
import com.my_test.test.Request.PictureGetInfoRequest;
import com.my_test.test.Request.PictureUploadRequest;
import com.my_test.test.Response.PictureGetByCategoryResponse;
import com.my_test.test.Response.PictureGetInfoResponse;
import com.my_test.test.Response.PictureUploadResponse;
import com.my_test.test.Services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<PictureUploadResponse> uploadImage(
            @RequestParam("userName") String userName,
            @RequestParam("category") String category,
            @RequestParam("creationIntent") String creationIntent,
            @RequestParam("imageFile") MultipartFile imageFile) {
        UserEntity user=userRepository.findByAccountName(userName);
        PictureUploadRequest request = new PictureUploadRequest(user, imageFile, creationIntent, Instant.now(), category);
        PictureUploadResponse response = pictureService.uploadImage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<PictureGetByCategoryResponse> getPictureByCategory(@RequestParam("category") String category) {
        PictureGetByCategoryRequest request = new PictureGetByCategoryRequest(category);
        PictureGetByCategoryResponse response = pictureService.getPictureByCategory(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/{imageId}")
    public ResponseEntity<PictureGetInfoResponse> getPictureInfo(@PathVariable("imageId") Long imageId) {
        PictureGetInfoRequest request = new PictureGetInfoRequest(imageId);
        PictureGetInfoResponse response = pictureService.getPictureInfo(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/like/{imageId}")
    public ResponseEntity<?> likePicture(@PathVariable("imageId") Long imageId) {
        try {
            pictureService.likePicture(imageId);
            return ResponseEntity.ok("图片点赞成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
