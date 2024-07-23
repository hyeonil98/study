package com.example.proj.service;

import com.example.proj.dto.User;
import com.example.proj.dto.UserCreateDto;
import com.example.proj.email.DummyVerificationEmailSender;
import com.example.proj.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserRepository userRepository;

    @Test
    public void 이메일_회원가입을_하면_가입_보류_상태가_된다() {
        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test1@test.com")
                .nickname("user1")
                .build();

        // when
        UserService userService = UserService.builder()
                .verificationEmailSender(new DummyVerificationEmailSender())
                .userRepository(userRepository)
                .build();
        User user = userService.register(userCreateDto);


        // then
        assertThat(user.isPending()).isTrue();
    }
}