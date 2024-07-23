package com.example.proj.dto;

import lombok.Builder;

public class User {
    private String email;
    private String nickname;
    private UserStatus status;
    private String verificationCode;

    @Builder
    public User(String email, String nickname, UserStatus status, String verificationCode) {
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.verificationCode = verificationCode;
    }

    public boolean isPending() {
        return this.status.equals(UserStatus.PENDING);
    }
}
