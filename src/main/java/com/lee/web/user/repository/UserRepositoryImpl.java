package com.lee.web.user.repository;

import com.lee.web.user.domain.QUser;
import com.lee.web.user.domain.User;
import com.lee.web.user.domain.UserRegisterDto;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.lee.web.user.domain.QUser.*;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository{
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public User getUser(String userId) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(userId)).fetchOne();
    }

    public void register(UserRegisterDto dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        em.persist(user);
    }
}
