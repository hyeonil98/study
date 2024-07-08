package com.example.proj.repository;

import com.example.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserJpaRepository {
    public User getById(long userId) {
        // 실질적 탐색 로직

        // 임의 코드
        return new User(1L, "id1", "password1");
    }
}
