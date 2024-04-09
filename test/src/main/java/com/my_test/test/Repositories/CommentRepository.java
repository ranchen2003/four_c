package com.my_test.test.Repositories;

import com.my_test.test.Entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // 查找与特定图片ID相关联的评论
    List<CommentEntity> findByPictureImageId(Long imageId);

    // 根据用户ID查找评论
    List<CommentEntity> findByUserId(Long userId);

}
