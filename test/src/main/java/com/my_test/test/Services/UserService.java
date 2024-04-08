package com.my_test.test.Services;

import com.my_test.test.Entities.UserEntity;
import com.my_test.test.Repositories.UserRepository;
import com.my_test.test.Request.UserRequest;
import com.my_test.test.Response.MyErrorException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMillis;

    // 用户注册的逻辑
    public UserEntity registerUser(UserRequest userRequest) {
        if (userRepository.findByAccountName(userRequest.getAccountName()).isPresent()) {
            throw new MyErrorException("账户名已存在");
        }

        UserEntity newUser = new UserEntity(userRequest.getAccountName(), userRequest.getNickname(),
                userRequest.getPassword(), userRequest.getAvatarUrl(), userRequest.getLikesCount(),
                userRequest.getFollowersCount(), userRequest.getRegistrationTime());

        return userRepository.save(newUser);
    }

    // 用户登录的逻辑
    public String loginUser(String accountName, String password) {
        UserEntity user = userRepository.findByAccountName(accountName)
                .orElseThrow(() -> new MyErrorException("登录失败：用户名或密码错误"));

        if (!user.getPassword().equals(password)) {
            throw new MyErrorException("登录失败：用户名或密码错误");
        }

        return generateToken(user);
    }

    // JWT生成逻辑
    private String generateToken(UserEntity user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + jwtExpirationInMillis);

        return Jwts.builder()
                .setSubject(user.getAccountName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
