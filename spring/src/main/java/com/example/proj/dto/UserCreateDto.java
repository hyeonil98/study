package com.example.proj.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateDto {
    private String email;
    private String nickname;

    @Builder
    public UserCreateDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
