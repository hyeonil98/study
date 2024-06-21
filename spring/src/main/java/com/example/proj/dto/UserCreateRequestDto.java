package com.example.proj.dto;

public class UserCreateRequestDto {
    public String username;
    public String password;
    public String email;
    public String address;
    public String gender;
    public int age;

    public UserCreateRequestDto(String username, String password, String email, String address, String gender, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }
}
