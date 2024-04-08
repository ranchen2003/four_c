package com.my_test.test.Repositories;

import com.my_test.test.Entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    // 根据用户ID查找图片
    List<PictureEntity> findByUserId(Long userId);

    // 根据分类查找图片
    List<PictureEntity> findByCategory(String category);
}
