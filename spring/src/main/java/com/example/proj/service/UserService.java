package com.example.proj.service;

import com.example.proj.dto.User;
import com.example.proj.dto.UserCreateDto;
import com.example.proj.dto.UserStatus;
import com.example.proj.email.VerificationEmailSender;
import com.example.proj.repository.UserRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Builder
public class UserService {

    private final UserRepository userRepository;
    private final VerificationEmailSender verificationEmailSender;

    public UserService(UserRepository userRepository, VerificationEmailSender verificationEmailSender) {
        this.userRepository = userRepository;
        this.verificationEmailSender = verificationEmailSender;
    }

    @Transactional
    public User register(UserCreateDto userCreateDto) {
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .status(UserStatus.PENDING)
                .verificationCode(UUID.randomUUID().toString())
                .build();
        User savedUser = userRepository.save(user);
        verificationEmailSender.send(user);
        return user;
    }

}

