package com.my_test.test.Controllers;

import com.my_test.test.Request.CommentDeleteRequest;
import com.my_test.test.Request.CommentUploadRequest;
import com.my_test.test.Response.CommentDeleteResponse;
import com.my_test.test.Response.CommentUploadResponse;
import com.my_test.test.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 用户在图片详情页面发布评论
    @PostMapping("/upload")
    public ResponseEntity<CommentUploadResponse> uploadComment(@RequestBody CommentUploadRequest request) {
        CommentUploadResponse response = commentService.uploadComment(request);
        return ResponseEntity.ok(response);
    }

    // 用户删除评论
    @DeleteMapping("/delete")
    public ResponseEntity<CommentDeleteResponse> deleteComment(@RequestBody CommentDeleteRequest request) {
        CommentDeleteResponse response = commentService.deleteComment(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
