package stream;

import java.util.Arrays;

public class MatchingExample {
    public static void main(String[] args) {
        int[] intArr = {1,2,3,4,5};

        boolean result = Arrays.stream(intArr)
                .allMatch(v -> v % 2 == 0);

        System.out.println("result = " + result);

        result = Arrays.stream(intArr)
                .anyMatch(v -> v % 2 == 0);

        System.out.println("result = " + result);

        result = Arrays.stream(intArr)
                .noneMatch(v -> v % 2 == 0);

        System.out.println("result = " + result);

    }
}
