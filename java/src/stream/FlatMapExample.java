package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlatMapExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("stream split");
        list1.add("i am best java engineer");

        list1.stream().flatMap(data -> Arrays.stream(data.split(" "))).forEach(System.out::println);


        List<String> list2 = List.of("10, 20, 30, 40, 50");
        list2.stream().
                flatMapToInt(data -> {
                    String[] strArr = data.split(",");
                    int[] intArr = new int[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        intArr[i] = Integer.parseInt(strArr[i].trim());
                    }
                    return Arrays.stream(intArr);
                }).forEach(System.out::println);
    }
}
