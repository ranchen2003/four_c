package com.my_test.test.Entities;

import lombok.Data;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user; // 所属用户

    // 构造函数
    public PictureEntity(UserEntity user, Integer likesCount, String url, String category, String creationIntent, Instant uploadTime) {
        this.user = user;
        this.likesCount = likesCount;
        this.url = url;
        this.category = category;
        this.creationIntent = creationIntent;
        this.uploadTime = uploadTime;
    }

    public PictureEntity() {

    }
}
