# 개인적으로 공부하는 자바 스터디

---
### Generic 
제네릭~~(Generic)은 결정되지 않은 타입에 대해 파라미터 T 로 처리하고,
실제 사용되는 파라미터를 구체적인 타입으로 대체시킨다.
```java
// content Type을 T라는 파라미터로 정의
public class Box <T>{
    public T content;
}
```

제네릭 클래스의 변수를 생성할 때 에는 생성자에 타입을 명시 하지 않아도 된다.
```java
public class GenericExample {
    public static void main(String[] args) {
        // 생성자 타입으로 자동 변환
        Box<String> box1 = new Box<>();
        box1.content = "안녕하세요";
        System.out.println("box1.getContent() = " + box1.getContent());

        Box<Integer> box2 = new Box<>();
        box2.content = 23;
        System.out.println("box2 = " + box2.getContent());
    }
}
```
```
실행 결과 
    안녕하세요
    23
```
---
### Generic Type
제네릭은 클래스와 인터페이스에도 사용 가능하다, 이를 제너릭 타입이라고 칭한다.
```java
public class Product <K, M>{
    private K kind;
    private M model;
}
```
```java
public interface Rentable<P>{
    P rent();
}
```
---
### Generic Method
제너릭 메소드는 타입 파라미터를 가지고 있는 메서드를 칭한다.
```java
// 매개변수로 T를 사용하고 T를 내용물로 가지는 Box객체를 리턴
public <T> Box<T> boxing(T t){}
```
---
### Generic 타입 파라미터 제한
경우에 따라서 타입 파라미터를 제한 할 수 있다.
```java
// Number 또는 Number의 자식 클래스로 타입 파라미터 제한
public <T extends Number> boolean compare(T t1, T t2){}
```

제너릭 타입을 매개변수나 리턴 타입으로 사용할 때 ?(와일드카드)
를 사용할 수 있다.
```java
// Student와 Student의 자식 클래스만 타입 파라미터 가능
public registerCource(Applicant <? extends Student> applicant){}
```
```java
// Worker와 Worker 부모 클래스만 타입 파라미터 가능
public registerCource(Applicant <? super Worker> applicant){}
```

```java
// 모든 타입 파라미터 가능
public registerCource(Applicant <?> applicant){}
```
