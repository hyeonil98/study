package com.lee.web.user.repository;

import com.lee.web.user.domain.User;
import com.lee.web.user.domain.UserRegisterDto;
import org.springframework.stereotype.Repository;

import static com.lee.web.user.domain.QUser.user;

@Repository
public interface UserRepository {
    User getUser(String userId);

    void register(UserRegisterDto dto);
        // Method to register a new user
        // This method should be implemented in the UserRepositoryImpl class
}
