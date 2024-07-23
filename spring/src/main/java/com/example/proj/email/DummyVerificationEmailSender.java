package com.example.proj.email;

import com.example.proj.dto.User;

import java.net.http.HttpConnectTimeoutException;

public class DummyVerificationEmailSender implements VerificationEmailSender{
    @Override
    public void send(User user) {
        // 원하는 비즈니스 로직 구현
    }
}
