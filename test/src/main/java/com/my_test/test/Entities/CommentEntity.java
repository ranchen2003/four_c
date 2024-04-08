package com.my_test.test.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id; // 对应数据库中的comment_id字段

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // 外键关联到用户表，对应数据库中的user_id字段

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private PictureEntity picture; // 外键关联到图片表，对应数据库中的image_id字段

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 评论内容，对应数据库中的content字段

    @Column(name = "comment_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime commentTime; // 评论时间，对应数据库中的comment_time字段

    // 构造函数
    public CommentEntity(UserEntity user, PictureEntity picture, String content, LocalDateTime commentTime) {
        this.user = user;
        this.picture = picture;
        this.content = content;
        this.commentTime = commentTime;
    }

    public CommentEntity() {

    }
}
