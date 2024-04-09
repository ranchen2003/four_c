package com.my_test.test.Repositories;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    // 根据用户ID查找图片，这个方法可以用来支持按用户获取图片的功能
    List<PictureEntity> findByUserId(Long userId);

    // 根据分类查找图片，这个方法已经包含在您的代码中，可以用来支持按分类获取图片列表的功能
    List<PictureEntity> findByCategory(String category);
}
