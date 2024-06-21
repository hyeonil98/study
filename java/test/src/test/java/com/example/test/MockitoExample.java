package com.example.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoExample {
    @Test
    public void mockitoTest() {
        int age = 20;
        MemberService memberService = Mockito.mock(MemberService.class);

        Member member = new Member(age, memberService);
        Mockito.when(member.ageAfterOneYear()).thenReturn(age + 1);

        // act
        int nextAge = member.ageAfterOneYear();

        // Assert
        Assertions.assertThat(nextAge).isEqualTo(age + 1);
        Mockito.verify(memberService, Mockito.times(1)).ageAfterOneYear(age);
    }
}
