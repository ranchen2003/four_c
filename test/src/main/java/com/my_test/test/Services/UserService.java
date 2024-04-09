package com.my_test.test.Services;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import com.my_test.test.Repositories.CommentRepository;
import com.my_test.test.Repositories.PictureRepository;
import com.my_test.test.Repositories.UserRepository;
import com.my_test.test.Request.*;
import com.my_test.test.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 用户登录的逻辑
    public UserLoginResponse loginUser(UserLoginRequest request) {
        UserEntity user = userRepository.findByAccountName(request.getUsername());
        if (user!=null&& user.getPassword().equals(request.getPassword())) {
            return new UserLoginResponse(true, "登录成功");
        } else {
            return new UserLoginResponse(false, "用户名或密码错误");
        }
    }

    // 用户注册的逻辑
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        if (userRepository.findByAccountName(request.getUsername())!=null) {
            return new UserRegisterResponse(false, "用户名已存在");
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setAccountName(request.getUsername());
            newUser.setPassword(request.getPassword());
            // 设置其他必要的默认值
            userRepository.save(newUser);
            return new UserRegisterResponse(true, "注册成功");
        }
    }

    // 更新用户信息的逻辑
    public UserUpdateResponse updateUser(UserUpdateRequest request) {
        UserEntity user = userRepository.findByAccountName(request.getUsername());
        if (user!=null) {
            // 更新用户信息逻辑，例如更新昵称等
            // user.setNickname(...);
            userRepository.save(user);
            return new UserUpdateResponse(true, "更新成功");
        } else {
            return new UserUpdateResponse(false, "用户不存在");
        }
    }

    // 获取用户信息的逻辑
    public UserGetInfoResponse getUserInfo(UserGetInfoRequest request) {
        UserEntity user = userRepository.findByAccountName(request.getUsername());
        if (user!=null) {
            Long userId = user.getId();
            // 根据用户ID获取图片列表
            List<PictureEntity> pictures = pictureRepository.findByUserId(userId);
            // 根据用户ID获取评论列表
            List<CommentEntity> comments = commentRepository.findByUserId(userId);

            return new UserGetInfoResponse(true, "获取成功", user, comments, pictures);
        } else {
            return new UserGetInfoResponse(false, "用户不存在", null, null, null);
        }
    }
}
