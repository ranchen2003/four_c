package com.my_test.test.Entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 用户ID

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName; // 账户名

    @Column(name = "nickname")
    private String nickname; // 昵称

    @Column(name = "password", nullable = false)
    private String password; // 密码

    @Column(name = "avatar_url")
    private String avatarUrl; // 头像URL

    @Column(name = "likes_count", nullable = false)
    private Integer likesCount = 0; // 获赞数

    @Column(name = "followers_count", nullable = false)
    private Integer followersCount = 0; // 粉丝数

    @Column(name = "registration_time", nullable = false)
    private Instant registrationTime; // 注册时间

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PictureEntity> pictures = new ArrayList<>(); // 用户发布的图片列表

    // 构造函数
    public UserEntity(String accountName, String nickname, String password, String avatarUrl, Integer likesCount, Integer followersCount, Instant registrationTime) {
        this.accountName = accountName;
        this.nickname = nickname;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.likesCount = likesCount;
        this.followersCount = followersCount;
        this.registrationTime = registrationTime;
    }

    public UserEntity() {

    }
}
