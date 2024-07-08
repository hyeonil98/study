# Spring Service Layer

----
서비스는 왜 서비스 이며 서비스란 무엇인지 알아보는 포스트가 되겠습니다.

스프링에서 서비스 계층은 도메인에 일을 위임해야 하는 공간이라고 할 수 있습니다.
이는 서비스 계층이 다음과 같은 일을 수행해야함을 의미합니다.
1. 도메인 객체를 불러옴
2. 도메인 객체나 도메인 서비스에 일을 임
3. 도메인 객체의 변경사항 반영

서비스가 무엇인지 알아보기 위해선 @Service 어노테이션을 살펴봐야 합니다.

![@Service](https://github.com/TwoEther/study/assets/101616106/38bc41e8-41ba-48b7-9a9b-ecfc0d23443a)

이를 요약하면 다음과 같습니다.
* @Service는 DDD에서 만들어진 애너테이션이다.
* 서비스는 J2EE패턴 중 하나인 '비즈니스 서비스 파사드'처럼 사용될 수 있다.

서비스는 DDD에서 기반한 애너테이션이라고 합니다, 그렇다면 DDD는 무엇인지 알아야 할 필요성이 생겼습니다.

DDD(Domain Driven Design)은 도메인을 중심에 놓고 소프트웨어를 설계하는 방법입니다, 여기서 말하는 도메인 이란 개발자영역에서
는 비즈니스 영역 혹은 해결하고자 하는 문제 영역이 될 수 있습니다.

DDD를 쉽게 이해하기 위해 다음과 같은 예를 들어볼까 합니다.

은행 시스템에서 도메인은 은행이라고 볼 수 있습니다, 은행 시스템에서 일을 하는 백엔드 개발자에게 요구되는 능력은 단순한 개발 능력 뿐만 아니라
'신용', '예금', '여신' 같은 용어를 잘 이해하고 있어야 합니다, 은행 시스템을 만들기 위해서는 은행 도메인의 문제를 해결하려고 하는지 잘 알고 있어야
한다는 뜻입니다.

DDD 에서 훌륭한 소프트웨어 개발자는 도메인에서 발생하는 문제를 해결하기 위해 도메인을 잘 알고 있어야 합니다, 일반적으로 개발자는 개발 외의 다른 도메인
에 대해서 잘 알지 못합니다, 그렇기 때문에 도메인을 분석하고 탐색할 수 있는 능력이 중요합니다.

도메인 탐색을 통해 도메인 지식을 높일 수 있습니다, 이러한 도메인 탐색을 가장 효과적으로 하는 방법은 도메인 전문가에게 물어보는 것 입니다, 은행으로 가정하면
은행원을 도메인 전문가라고 칭할 수 있습니다, 이러한 이유로 DDD에서는 도메인 전문가와 개발자의 협력을 중요시 합니다.

DDD에서는 도메인 문제를 해결하기 위해 몇 가지 컴포넌트를 제공합니다, 그 중에 service 컴포넌트가 있습니다.
서비스는 도메인 객체가 처리하기 애매한 연산 자체를 표현하기 위한 컴포넌트 입니다.

```java
@Service
public class ProductService {
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CouponJpaRepository couponJpaRepository;

    public ProductService(UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository, CouponJpaRepository couponJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.couponJpaRepository = couponJpaRepository;
    }

    // 최대 할인율을 찾는 코드
    public int calculatePrice(long userId, long productId) {
        User user = userJpaRepository.getById(userId);
        Product product = productJpaRepository.getById(productId);
        List<Coupon> coupons = couponJpaRepository.getByUserId(userId);

        Coupon maxDiscountCoupon = findMaxDiscount(coupons);

        // 쿠폰 적용
        int price = product.getPrice() - (int) (1 - maxDiscountCoupon.getDiscount());

        // 마일리지 반영
        price -= user.getMileage();
        return price;
    }

    public Coupon findMaxDiscount(List<Coupon> coupons) {
        return coupons.stream().max(Comparator.comparing(Coupon::getDiscount)).orElse(new Coupon(0L, 1));
    }
}
```

사용자가 가지고 있는 쿠폰중 가장 할인율이 높은 쿠폰을 찾고 상품의 가격에서 차감하는 코드입니다, 할인율 계산은 다음과 같습니다.

가격 = 상품 가격 X (1 - 쿠폰 최대 할인율) - 사용자 마일리지

되도록이면 서비스에 있는 비즈니스 로직은 도메인 객체가 처리해야 합니다.
1. user.calculate(coupons, product) -> 유저가 가격 계산
2. product.calculate(user, product) -> 쿠폰이 가격 계산
3. product.calculate(user, coupons) -> 상품이 가격 계산

하지만 위의 경우 로직 문맥상 어색합니다, 가격 계산 로직은 그 자체로 연산이며 행동이기 때문입니다,
따라서 별도의 클래스를 만들어 로직을 분리하여야 합니다.

```java
@Service
public class ProductService {
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CouponJpaRepository couponJpaRepository;

    public ProductService(UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository, CouponJpaRepository couponJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.couponJpaRepository = couponJpaRepository;
    }

    // 최대 할인율을 찾는 코드
    public int calculatePrice(long userId, long productId) {
        User user = userJpaRepository.getById(userId);
        Product product = productJpaRepository.getById(productId);
        List<Coupon> coupons = couponJpaRepository.getByUserId(userId);

        Coupon maxDiscountCoupon = findMaxDiscount(coupons);
        PriceManager priceManager = new PriceManager();

        return priceManager.calculate(user, product, maxDiscountCoupon);
    }

}
```
Manager 클래스를 통해 비즈니스 로직을 분리하였습니다, Manager 클래스란 모델과 관련된 부가적인 논리 로직을 처리하는 클래스 라고
생각하면 될것 같습니다.

PriceManager는 도메인 시스템을 구축하기 위해 존재합니다, PriceManager는 가격을 계산한다는 점에서 도메인에 가까운 로직이라고 볼 수 있습니다,
반면에 ProductService는 도메인에 필요한 비즈니스 로직을 가지고 있기 보다는 애플리케이션이 돌아가는데 필요한 연산을 가지고 있는 서비스 입니다,
따라서 도메인 보다는 '실행'에 맞추어 개발된 서비스라고 할 수 있습니다.

도메인과 도메인 서비스, 애플리케이션 서비스를 구분지어야 할 필요가 있습니다.

| 분류                | 역할                          | 주요 행동                          | 예시                  |
|---------------------|-------------------------------|------------------------------------|-----------------------|
| 도메인              | 비즈니스 로직을 처리          | 도메인 역할 수행 및 도메인 협력    | User, Product, Coupon |
| 도메인 서비스       | 비즈니스 '연산' 로직을 처리   | 도메인 협력 중재 및 연산 로직 처리 | PriceManager          |
| 애플리케이션 서비스 | 애플리케이션 '연산' 로직 처리 | 도메인 불러오기 및 서비스 실행     | ProductManager        |

중요한 점은 도메인이 비즈니스 로직을 가져야 한다는 것 입니다, 같은 의미로 스프링 서비스 컴포넌트에 너무 많은 역할이 할당되어선 안됩니다.





