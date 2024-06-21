package stream;

import java.util.Arrays;

public class LoopingExample {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 3, 4, 5};

        int total = Arrays.stream(intArr)
                .filter(v -> v % 2 == 0)
                .sum();
        System.out.println("total = " + total);

        Arrays.stream(intArr).
                filter(v -> v%2 == 0)
                .forEach(System.out::println);
    }
}
