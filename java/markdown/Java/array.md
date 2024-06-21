# 자바 가변 배열

----
자바 배열에는 고정 크기 배열과 가변 배열이 있습니다, 오늘은 가변 배열에 대해 알아보겠습니다.

가변 배열은 고정 크기 배열과 다르게 정해진 크기에 따라 할당되는 것이 아닙니다 ,다음과 같은 예를 보겠습니다.

```java
public static void createFixedArray() {
    /*
        다음은 고정 크기 배열의 선언 방법입니다.
        선언과 동시에 초기화가 가능하며 메모리 할당만 할 수 있습니다.
        할당된 배열의 맴버 변수는 heap 영역에 저장됩니다
    */
    int[] arr1 = new int[3];
    int[] arr2 = {1, 2, 3};

    // ArrayIndexOutOfBoundsException 발생
    for (int i = 0; i < 4; i++) {
        arr1[i] = i;
    }
}
```

고정 크기의 배열같은 경우 일단 선언되면 크기를 변경하는 것은 불가합니다,
선언된 크기에 초과하는 접근이 발생하는 경우 ArrayIndexOutOfBoundsExceptiond이 발생합니다.

```java
import java.util.ArrayList;

public static void createMutableArray() {
    // 10개 int 크기의 배열 선언
    ArrayList<Integer> fixedArray = new ArrayList<>(10);
    ArrayList<Integer> array = new ArrayList<>();
    int size = 10;
    for (int i = 0; i < size; i++) {
        array.add(i);
    }
    System.out.println(array);
}
```

가변 배열의 경우 ArrayList를 사용합니다, ArrayList는 고정 크기 배열과 같이 크기를 정해서 사용할 수 있습니다.


