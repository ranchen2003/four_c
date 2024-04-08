package com.my_test.test.Repositories;

import com.my_test.test.Entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // 根据图片ID查找评论
    List<CommentEntity> findByPictureId(Long pictureId);

    // 根据用户ID查找评论
    List<CommentEntity> findByUserId(Long userId);
}
