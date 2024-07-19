# Spring AOP

----
AOP(Aspect Oriented Programming)은 관점 지향 프로그래밍으로 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점을
기준으로 각각 모듈화 하는 방식입니다.
이때 모듈화란 어떤 공통된 로직이나 기능을 하나로 묶는것 입니다.

AOP에서 어떠한 기준으로 로직을 모듈화 한다는 것은 코드를 부분적으로 나누어 모듈화 하겠다는 것이다.
이때 코드 상에서 공통된 코드를 발견할 수 있는 데 이것들을 흩어진 관심사 라고 합니다.

### AOP 주요 개념
* Aspect : 관심사들을 모듈화 한 것
* Target : Aspect를 적용하는 곳(Class, Method 등)
* Advice : 실질적 로직 혹은 구현체
* JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점, 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
* PointCut : JointPoint의 상세스펙을 정의.

### Spring AOP의 특징
* Proxy 기반의 AOP 구현체
* Spring Bean에만 AOP 적용가능
* Spring IOC와 연동하여 중복코드, 복잡도 증가 등의 문제에 대한 해결책 제시

### AOP 적용 방식
AOP 적용 방식은 크게 3가지 입니다

* 컴파일 시점
* 클래스 로딩 시점
* 런타임 시점

주로 런타임 시점을 적용합니다, 런타임 시점의 경우 프록시를 사용하기 때문에 스프링이 알아서 기본 정보를 설정해주어 사용에 편리합니다.
런타임 시점 AOP의 경우 메소드 실행 지점에만 AOP를 적용할 수 있습니다, 또한 프록시 방식을 사용하기 때문에 Spring Container에 @Aspect 어노테이션을 사용하여 등록해주어야 합니다.


스프링 부트에서는 AnnotationAwareAspectJAutoProxyCreator라는 자동 프록시 생성기에 의해
@Aspect 어노테이션이 붙은 클래스를 Advisor로 변환하여 저장합니다, 그 후에 Advisor를 찾아와 프록시를
생성하여 PointCut을 보고 프록시가 필요한 곳에 Advice를 적용합니다.

스프링에서 @Aspect를 사용하여 Advisor로 사용할 클래스를 지정할 수 있습니다, 주의할 점 으로 private, final 메소드는 AOP가 적용불가 합니다.


```java
@Slf4j
@Component
@Aspect
public class AspectExample {
    @Around("execution(* com.example.aop..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point
        return joinPoint.proceed(); // 실제 타겟 호출
    }
}
```
AOP를 적용한 클래스 입니다, @Around 안에 매개 변수를 통해 com.example.aop 패키지와 하위
패키지를 지정하는 포인트컷 표현식을 작성하여 doLog 어드바이스가 적용됩니다.
