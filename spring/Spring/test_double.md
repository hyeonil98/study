# Spring Test 대역에 대한 고찰

----


```java
@Service
@Builder
public class UserService {

    private final UserRepository userRepository;
    private final VerificationEmailSender verificationEmailSender;

    @Transactional
    public User register(UserCreateDto userCreateDto) {
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .status(UserStatus.PENDING)
                .verificationCode(UUID.randomUUID().toString())
                .build();
        User savedUser = userRepository.save(user);
        verificationEmailSender.send(user);
        return user;
    }

}
```
사용자가 시스템에 회원가입을 하는 상황을 표현하는 코드 입니다, 사용자가 이메일 인증이 안 된 상태라고 가정하기 때문에 가입 보류 상태로 지정합니다,
이때 UserRepository와 VerificationEmailSender는 인터페이스 이며 JPA와의 의존성을 제거한 상태입니다.

만일 UserService.register의 메소드에 실제 이메일을 발송하는 코드가 있다면 테스트 실행마다 이메일이 발송되는 상황이 발생합니다,
테스트의 목적이 실제 이메일 발송여부에 대해 확인하고 싶을수도 있지만 이미 verificationEmailSender.send의 메소드에서 메일 전송여부를 확인하고 있을것 입니다,
따라서 UserService.register 에서는 사용자의 상태가 UserStatus.PENDING인 상태로 저장소의 저장 여부만 확인하면 됩니다.


```java
public class DummyVerificationEmailSender implements VerificationEmailSender{
    @Override
    public void send(User user) {
        // 원하는 비즈니스 로직 구현
    }
}
```

 ```java
class UserServiceTest {
    private UserRepository userRepository;

    @Test
    public void 이메일_회원가입을_하면_가입_보류_상태가_된다() {
        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test1@test.com")
                .nickname("user1")
                .build();

        // when
        UserService userService = UserService.builder()
                .verificationEmailSender(new DummyVerificationEmailSender())
                .userRepository(userRepository)
                .build();
        User user = userService.register(userCreateDto);


        // then
        assertThat(user.isPending()).isTrue();
    }
}
```
verificationEmailSender 대신에 가짜 객체 DummyVerificationEmailSender를 사용하였습니다, 이 처럼 테스트 대역은
실제 객체를 대신하는 객체를 의미합니다.

테스트 대역의 5가지 유형은 다음과 같습니다.

| 유형  | 설명                                                  |
|-------|-------------------------------------------------------|
| Dummy | 아무런 동작을 하지 않습니다                           |
| Stub  | 지정한 값만 반환                                      |
| Fake  | 자체적 로직 존재                                      |
| Mock  | 아무런 동작을 하지 않지만 어떤 행동이 호출됐는지 기록 |
| Spy   | 실제 객체와 똑같이 행동하고 모든 행동 호출 기록       |



