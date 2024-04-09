package com.my_test.test.Repositories;

import com.my_test.test.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 根据账户名查找用户
    UserEntity  findByAccountName(String accountName);
}
