package com.example.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExample {
    @Test
    @DisplayName("연산 테스트")
    public void addTest() {
        assertThat(2 + 3).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 3})
    @DisplayName("인자를 받는 연산 테스트")
    public void parameterizedTest(int argument) {
        System.out.println("argument = " + argument);
    }

    @Nested
    @DisplayName("class 단위 테스트")
    class methodOne {
        @BeforeEach
        void setUp() {
            System.out.println("setUp called!");
        }

        @Test
        void testOne() {
            System.out.println("testOne called!");
        }
        @Test
        void testTwo() {
            System.out.println("testTwo called!");
        }
    }
}
