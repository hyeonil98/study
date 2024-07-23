package com.example.proj.email;

import com.example.proj.dto.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationEmailSender {
    public void send(User user);
}
