# Spring 에서 의존관계를 주입하는 방법

---
 Spring Container 에서는 Spring Bean Repository에 Bean name과 Bean 객체를 저장합니다,
 이때 @Bean 어노테이션을 사용하여 저장할 수 있으며 수동으로 Bean 이름을 지정할 수 있습니다.

상황에 따라 의존관계가 존재할 수 있습니다, 다음과 같은 예를 보겠습니다.
```java
@Bean
public MemberService memberService(){
    return new MemberServiceImpl(memberRepository());
}

@Bean
public OrderService orderService(){
    return new OrderServiceImpl(
            memberRepository(),
            discountPolicy());
}

@Bean
public MemberRepository memberRepository(){
    return new MemberRepositoryImpl();
}

@Bean
public DiscountPolicy discountPolicy(){
    return new RateDiscountPolicy();
}
```

orderService는 memberReposioty와 discountPolicy를 의존합니다, 따라서 Spring Container에서는 
이러한 정보를 참조하여 의존관계를 주입합니다(Dependency Injection).


의존관계를 주입하는 방법은 크게 4가지가 있습니다.
1. 생성자 주입
2. setter 주입
3. 필드 주입
4. 일반 메소드 주입

##### 생성자 주입
생성자를 통해 1번만 호출됩니다, 불변 및 필수 의존관계에서 사용합니다.
```java
@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    
    // 생성자가 1개만 존재할 경우 Autowired 생략 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
        
}
```
평이한 경우 생성자 주입을 통해 의존관계를 주입하는것이 올바른 방법이다(변경 방지)

##### Setter 주입
setter 수정자 메소드를 통해 의존관계를 주입하는 방법이다, 변경 가능성이 있는 의존관계 에서 사용한다.
```java
@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    
    // 생성자가 1개만 존재할 경우 Autowired 생략 가능
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }
}
```

##### 필드 주입
필드에 @Autowired 어노테이션을 사용하여 바로 주입하는 방법이다, 외부에서 변경이 불가하기 때문에
테스트 하기 힘들다는 단점이 있다, DI 프레임워크가 없으면 동작할 수 없기 때문에 사용을 권하지 않는다.

```java
@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final DiscountPolicy discountPolicy;
}
```

##### 일반 메소드 주입
일반 메소드에 @Autowired 어노테이션을 사용하여 바로 주입하는 방법이다, 일반적으로 잘 사용하지 않는다.

```java
@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자가 1개만 존재할 경우 Autowired 생략 가능
    @Autowired
    public void init(MemberRepository memberRepository,
                            DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

개발할때 특수한 경우를 제외하고 생성자 주입을 사용하면 될 것 같다.