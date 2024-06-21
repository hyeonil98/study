# 자바 Stream 

자바 스트림은 자바 8 Version 에서 추가된 문법입니다, 스트림을 사용하여 
배열이나 컬렉션 같은 자료구조 에서 for 또는 foreach, Iterator 구문을 순환하여 요소를 하나씩 접근하여 처리할 수 있습니다.

스트림은 배열 또는 컬렉션 같은 자료구조에 함수 여러 개를 조합하여 원하는 결과를 필터링 하고 가공된 결과를 얻을 수 있습니다.
또한 Lambda를 이용하여 코드의 가독성을 높일수 있습니다.

스트림은 병렬처리(Multi Threading)가 가능합니다, 따라서 많은 작업들을 빠르게 처리할 수 있습니다.

스트림은 다음과 같은 파이프라인을 가지고 있습니다.
```html
생성 스트림 -> 중간 스트림 -> 최종 작업 스트림
```

* 생성 스트림은 컬렉션 자료구조를 스트림으로 변환하는 역할을 합니다.
* 중간 스트림은 필터링, 매핑 등 최종 처리를 위한 작업을 수행합니다.
* 최종 스트림은 중간 스트림에서 정제된 요소들을 반복하거나 계산과 같은 작업을 수행합니다.

### 생성 스트림
스트림의 경우 배열과 컬렉션 같은 자료구조로 만드는 것이 일반적인 방법 이지만, 다양한 방법으로 스트림을 만들수 있습니다.

#### 배열 스트림
배열의 경우 Arrays.stream 메소드를 이용하여 스트림을 생성할 수 있습니다.

```java
import java.util.Arrays;
import java.util.stream.Stream;

String[] array = {"a", "b", "c", "d"};
Stream<String> stream = Arrays.stream(array);
Stream<String> streamOfArrayPart = Arrays.stream(arra,1,3); // 1~2 슬라이스
```
List, Map, Collection의 경우 디폴트 메소드 stream을 이용하여 스트림을 만들 수 있습니다.

```java
// Collection Interface 중 일부
default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
}
```

```java
import java.util.Arrays;

List<String> list = List.of("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
```

이 외에도 문자열 스트림, 파일 스트림, 병렬 스트림이 있습니다.

#### 중간 스트림
중간 스트림에서는 Filter, Map 등을 활용하여 원하는 데이터 만을 추출할 수 있습니다.

#### Filtering
필터는 스트림 내 요소들을 걸러내는 작업입니다.
```java
 public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("Person"+(i+1));
            list.add("Person"+(i+1));
        }

        // 중복 제거
        list.stream().distinct().forEach(System.out::println);
        System.out.println("===============================");
        // 필터링 1이 포함된 요소만 추출
        list.stream().filter(s -> s.contains("1"))
                .forEach(System.out::println);
    }
```

#### Map
Map은 스트림 내 요소들을 특정 값으로 변환할 수 있습니다, 이때 값을 변환하기 위한 Lambda를 인자로 받습니다.
스트림에 들어간 값이 특정 로직을 거친 후 output 이 되어 새로운 스트림에 담기게 되는데 이 작업을 Mapping 이라고 합니다.

```java
public class Student implements Comparable<Student>{
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(score, o.score);
    }
}


 public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        int[] intArray = {1, 2, 3, 4, 5};

        for (int i = 1; i < 10; i++) {
            list.add(new Student("person"+i, (int)(Math.random()*100)));
        }
        
        // 전체 학생의 각 score 출력
        list.stream().mapToInt(Student::getScore).forEach(System.out::println);
        System.out.println("---------------------------");

        IntStream intStream1 = Arrays.stream(intArray);
        intStream1.asDoubleStream().forEach(System.out::println);
        System.out.println("---------------------------");

        IntStream intStream2 = Arrays.stream(intArray);
        intStream2.boxed().forEach(System.out::println);

    }
```