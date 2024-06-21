package com.example.test;

public class Member {
    private int age;
    private final MemberService memberService;

    public Member(int age, MemberService memberService) {
        this.age = age;
        this.memberService = memberService;
    }
    
    public int ageAfterOneYear() {
        return memberService.ageAfterOneYear(age);
    }
}

