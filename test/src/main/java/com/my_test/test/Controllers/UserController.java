package com.my_test.test.Controllers;

import com.my_test.test.Entities.UserEntity;
import com.my_test.test.Repositories.UserRepository;
import com.my_test.test.Request.UserGetInfoRequest;
import com.my_test.test.Request.UserLoginRequest;
import com.my_test.test.Request.UserRegisterRequest;
import com.my_test.test.Request.UserUpdateRequest;
import com.my_test.test.Response.UserGetInfoResponse;
import com.my_test.test.Response.UserLoginResponse;
import com.my_test.test.Response.UserRegisterResponse;
import com.my_test.test.Response.UserUpdateResponse;
import com.my_test.test.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public UserRegisterResponse registerUser(@RequestBody UserRegisterRequest request) {
        if (userRepository.findByAccountName(request.getUsername())!=null) {
            return new UserRegisterResponse(false, "用户名已存在");
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setAccountName(request.getUsername()); // 确保此处赋值
            newUser.setPassword(request.getPassword());
            newUser.setRegistrationTime(Instant.now());
            userRepository.save(newUser);
            return new UserRegisterResponse(true, "注册成功");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request) {
        UserUpdateResponse response = userService.updateUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<UserGetInfoResponse> getUserInfo(@RequestParam String username) {
        UserGetInfoRequest request = new UserGetInfoRequest(username);
        UserGetInfoResponse response = userService.getUserInfo(request);
        if(response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

}
