package com.my_test.test.Repositories;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import com.my_test.test.Repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class test {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CommentRepository commentRepository;

    private UserEntity testUser;
    private PictureEntity testPicture;
    private CommentEntity testComment;

    @BeforeEach
    public void setup() {
        // 创建用户
        testUser = new UserEntity();
        testUser.setAccountName("testUser");
        testUser.setPassword("testPass");
        testUser.setRegistrationTime(Instant.now());
        testUser = userRepository.save(testUser);

        // 创建图片
        testPicture = new PictureEntity();
        testPicture.setUrl("http://example.com/test.jpg");
        testPicture.setUploadTime(Instant.now());
        testPicture.setUser(testUser);
        testPicture = pictureRepository.save(testPicture);

        // 创建评论
        testComment = new CommentEntity();
        testComment.setContent("Great Picture!");
        testComment.setCommentTime(LocalDateTime.now());
        testComment.setUser(testUser);
        testComment.setPicture(testPicture);
        testComment = commentRepository.save(testComment);
    }

    @Test
    @Rollback(false)
    public void testCreateReadUpdateDelete() {
        // 测试读取
        assertNotNull(userRepository.findById(testUser.getId()));
        assertNotNull(pictureRepository.findById(testPicture.getImageId()));
        assertNotNull(commentRepository.findById(testComment.getId()));

        // 测试更新
        testUser.setNickname("UpdatedName");
        userRepository.save(testUser);
        assertEquals("UpdatedName", userRepository.findById(testUser.getId()).get().getNickname());

        // 测试删除
        commentRepository.delete(testComment);
        pictureRepository.delete(testPicture);
        userRepository.delete(testUser);

        assertTrue(commentRepository.findById(testComment.getId()).isEmpty());
        assertTrue(pictureRepository.findById(testPicture.getImageId()).isEmpty());
        assertTrue(userRepository.findById(testUser.getId()).isEmpty());
    }

    @AfterEach
    public void tearDown() {
        // 确保所有实体都被清理，避免污染其他测试
        commentRepository.deleteAll();
        pictureRepository.deleteAll();
        userRepository.deleteAll();
    }
}
