# VO 와 DTO, DAO

----

VO는 다음 3가지 원칙을 만족합니다.
* 불변성 : 값은 변하지 않습니다.
* 동등성 : 값의 가치는 항상 같습니다.
* 자가 검증 : 값은 그 자체로 올바릅니다.

다음과 같은 객체를 VO라고 할 수 있을까요?
```java
public class Color {
    public int r;
    public int g;
    public int b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}

```
Color 객체는 외부 접근에 의해 값이 변경될 수 있습니다, 이는 VO 원칙의 불변성을 위반합니다,
자바에서는 불변성을 지원하기 위해 final 키워드를 제공합니다.

```java
public class Color {
    public final int r;
    public final int g;
    public final int b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
```

모든 맴버변수를 final로 선언한다면 VO가 될수 있을까요? 
답은 아닙니다, 맴버 변수에 참조 타입 객체가 있다면 불변성을 보장받지 못할 수 있습니다.

```java
import java.awt.*;

@Getter
public class FilledColor {
    public final int r;
    public final int g;
    public final int b;
    public Shape shape;

    public FilledColor(int r, int g, int b, Shape shape) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.shape = shape;
    }
}

@Data
public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
```

```java
import object.FilledColor;
import object.Rectangle;

Rectangle rectangle = new Rectangle(10, 20);
FilledColor filledColor = new FilledColor(1, 0, 0, rectangle);

// 불변성이 깨지는 코드
filledColor.getRectangle().setWidth(20);
```

따라서 모든 맴버 변수를 final로 생성한다고 해서 반드시 불변이 되는 것은 아닙니다, 
그렇기 때문에 VO의 불변성 자체에 주목해야 합니다, 불변성을 지켜야하는 이유는 불변성을 가진 객체는 내부 상태가 변경되지 않습니다,
따라서 항상 예측가능한 객체를 만들기 위해서는 불변성을 지켜야합니다.

반대로 예측할 수 없게 동작한다는 것은 어떤 의미일까요?
```java
public enum AccountLevel {
    DIAMOND,
    GOLD,
    SILVER,
    BRONZE
}


public class AccountInfo {
    private long mileage;
    public AccountLevel getLevel() {
        if (mileage > 100_100) {
            return AccountLevel.DIAMOND;
        } else if (mileage > 50_000) {
            return AccountLevel.GOLD;
        } else if (mileage > 30_000) {
            return AccountLevel.SILVER;
        } else return AccountLevel.BRONZE;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }
}
```

서로다른 Thread에 대해 같은 AccountInfo 객체를 접근하는 상황을 가정합니다, A 스레드에서 
객체에 마일리지를 할당하고 레벨 정보를 가져옵니다, 이때 B 스레드에서 마일리지를 변경하고 A 스레드에서
같은 객체에 메소드를 호출하면 getLevel의 호출결과가 달라집니다, 이러한 메서드를 실제 비즈니스 로직에 쓴다면
많은 문제를 야기할 것 입니다.

비즈니스 로직을 개발할 때에는 항상 멀티 스레드 환경에서 불변성을 유지할 수 있도록 개발해야 합니다.

```java
public class AccountInfo {
    private final long mileage;
    public AccountLevel getLevel() {
        if (mileage > 100_100) {
            return AccountLevel.DIAMOND;
        } else if (mileage > 50_000) {
            return AccountLevel.GOLD;
        } else if (mileage > 30_000) {
            return AccountLevel.SILVER;
        } else return AccountLevel.BRONZE;
    }

    public AccountInfo(long mileage) {
        this.mileage = mileage;
    }

    public AccountInfo withMileage(long mileage) {
        return new AccountInfo(mileage);
    }
}

```

final 키워드를 추가하면 객체의 변경이 일어날때 새로운 객체를 참조하게끔 동작합니다, 따라서
서로 같은 객체의 참조를 막을 수 있습니다.

DTO(Data Transfer Object) 데이터 전송객체는 전송을 목적으로 만들어진 객체입니다.
```java
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

```

DTO는 오로지 데이터를 효과적으로 전달하기 위한 역할을 담당합니다, 고로 DTO에는 다른 
비즈니스 로직이 포함되면 안됩니다.

DTO의 목적은 데이터를 전달하는 것 입니다, 그러므로 데이터를 전달하는 모든 상황에서 쓰일수 있습니다,
필요한 데이터를 전달할 때 매개변수를 일일이 나열하는 것이 부담스러워 DTO를 사용할 수 있고, 여러번 호출되는
메소드를 한번만 호출하기 위해서도 사용할 수 있습니다.
그렇기 때문에 DTO는 데이터 전송이 필요한 모든곳에 사용될 수 있습니다.


DAO(Data Access Object) 데이터 접근객체는 다음과 같은 역할을 담당합니다.

1. DB와의 연결 관리
2. 데이터에 대한 CRUD 수행
3. 보안 취약성을 고려한 쿼리 작성

DAO를 만들어야 하는 이유는 도메인 로직과 DB 연결 로직을 분리하기 위해서 입니다.
개발자들은 DB와 연결된 로직을 작성해야 할 경우가 매우 많습니다, 실제 개발 과정에서
비즈니스 로직과 DB 로직이 분리되어야 원활한 개발을 할 수 있다는 것은 누구나 아는 사실일 것 입니다.
따라서 DAO를 통해 이를 분리하고 원활한 개발환경을 구축 할 수 있습니다.
