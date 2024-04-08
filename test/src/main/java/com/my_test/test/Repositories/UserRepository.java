package com.my_test.test.Repositories;

import com.my_test.test.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 根据账户名查找用户
    Optional<UserEntity> findByAccountName(String accountName);

    // 根据昵称查找用户
    Optional<UserEntity> findByNickname(String nickname);
}
