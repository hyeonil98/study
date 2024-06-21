package stream;

import java.util.ArrayList;
import java.util.List;

public class FilteringExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("Person"+(i+1));
            list.add("Person"+(i+1));
        }

        // 중복 제거
        list.stream().distinct().forEach(System.out::println);
        System.out.println("===============================");
        // 필터링
        list.stream().filter(s -> s.contains("1"))
                .forEach(System.out::println);
    }
}
