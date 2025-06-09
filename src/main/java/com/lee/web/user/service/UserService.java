package com.lee.web.user.service;

import com.lee.web.aop.LogExecutionTime;
import com.lee.web.user.domain.User;
import com.lee.web.user.domain.UserRegisterDto;
import com.lee.web.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @LogExecutionTime
    public User getUserId(String userId) {
        return userRepository.getUser(userId);
    }

    @LogExecutionTime
    @Transactional
    public void register(UserRegisterDto dto) {
        userRepository.register(dto);
    }
}
