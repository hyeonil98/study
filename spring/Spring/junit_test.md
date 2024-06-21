# 테스트 주도 개발의 중요성과 Junit5를 통한 테스트 코드 작성

---- 
개인 개발이나 실무에서 테스트 주도 개발은 무척이다 중요하다, 기능 개발에 많은 시간을 투자한채 테스트를 
짤 시간이 없다는 핑계로 잘 작성하지 않는 것이 대부분이다, 프로그램이 커지면 커질수록 오류를 잡기 힘들것이다,
테스트 코드는 이러한 오류를 사전에 점검하고 해결하는데 상당한 도움을 준다, 테스트 코드에 대해 알고 고민하는 과정을 담을것 이다.

---

## Junit
Spring 에서는 테스트 개발을 위해 Junit이라는 프레임워크를 제공한다, Junit을 통해
단위 및 통합 테스트를 시행할 수 있다.

### Junit 사용법

Junit 에서는 @Test 혹은 @PrameterizedTest 어노테이션이 붙은 메소드를 실행시킨다, 
@Test는 인자가 없는 테스트, @PrameterizedTest는 인자가 있는 테스트를 반복해서 실행시킬수 있다.

```java
public class TestExample {
    @Test
    @DisplayName("연산 테스트")
    public void addTest() {
        assertThat(2 + 3).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
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
```
![test1](https://github.com/TwoEther/study/assets/101616106/d8cf3d5b-7a5c-4438-a3ec-cbc089b61d2f)
![test2](https://github.com/TwoEther/study/assets/101616106/bd9190fd-b1ff-4c52-b204-f5bd782ac45c)

```java
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

public class MemberService {
    public int ageAfterOneYear(int age) {
        return age + 1;
    }
}

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
```
Mockito는 Java 에서 사용되는 모의 객체를 위한 라이브러리 입니다, 테스트하려는 객체가
의존하는 다른 객체들을 쉽게 모의 객체로 만들어서 테스트 할 수 있습니다, 위 단위 테스트 관점에서는
MemberService의 의존성을 단절시켜야 하기 때문에 Mockito를 통해 MemberService 모의 객체를 만들어서 테스트를 진행하였습니다.

Mockito 같은 경우 when, thenReturn 을 통해 모의 객체의 테스트 여부를 검증할 수 있습니다.

----
Junit을 통해 통합테스트 또한 진행할 수 있습니다, 통합테스트용 어노테이션은 다음과 같이 있습니다.
* @SpringBootTest : 전체 Application Context 로드
* @WebMvcTest : Web Layer 에 필요한 Bean과 스프링 MVC에 필요한 구성 로드
* @DataJpaTest : JPA 관련 설정과 DB 상호작용을 테스트