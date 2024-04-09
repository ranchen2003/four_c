package com.my_test.test.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 评论ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // 发表评论的用户

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private PictureEntity picture; // 评论所属的图片

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 评论内容

    @Column(name = "comment_time", nullable = false)
    private LocalDateTime commentTime; // 评论时间
}
