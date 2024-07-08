package com.example.domain;

import lombok.Getter;

@Getter
public class User {
    private long userId;
    private String user_Id;
    private String password;
    private int mileage;

    public User(long userId, String user_Id, String password) {
        this.userId = userId;
        this.user_Id = user_Id;
        this.password = password;
        this.mileage = 0;
    }
}
