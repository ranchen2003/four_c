package com.my_test.test.Services;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Repositories.CommentRepository;
import com.my_test.test.Request.CommentDeleteRequest;
import com.my_test.test.Request.CommentUploadRequest;
import com.my_test.test.Response.CommentDeleteResponse;
import com.my_test.test.Response.CommentUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 用户在图片详情页面发布评论
    public CommentUploadResponse uploadComment(CommentUploadRequest request) {
        CommentEntity newComment = new CommentEntity();
        newComment.setUser(request.getUserEntity());
        newComment.setPicture(request.getPictureEntity());
        newComment.setContent(request.getComment());
        newComment.setCommentTime(LocalDateTime.now());

        CommentEntity savedComment = commentRepository.save(newComment);

        return new CommentUploadResponse(true, "评论发布成功", savedComment);
    }

    // 用户删除评论
    public CommentDeleteResponse deleteComment(CommentDeleteRequest request) {
        Long commentId = request.getCommentId();
        CommentEntity comment = commentRepository.findById(commentId).orElse(null);

        if (comment != null && comment.getUser().equals(request.getUserEntity())) {
            commentRepository.delete(comment);
            return new CommentDeleteResponse(true, "评论删除成功");
        } else {
            return new CommentDeleteResponse(false, "评论删除失败，评论不存在或用户不匹配");
        }
    }
}
