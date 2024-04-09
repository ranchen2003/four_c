package com.my_test.test.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Data
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId; // 图片ID

    @Column(nullable = false)
    private Integer likesCount = 0; // 获赞数

    @Column(nullable = false)
    private String url; // 图片URL

    @Column
    private String category; // 分类

    @Column
    private String creationIntent; // 创建意图

    @Column(nullable = false)
    private Instant uploadTime; // 上传时间

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // 发布图片的用户
}
