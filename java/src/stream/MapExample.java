package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MapExample {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        int[] intArray = {1, 2, 3, 4, 5};

        for (int i = 1; i < 10; i++) {
            list.add(new Student("person"+i, (int)(Math.random()*100)));
        }

        list.stream().mapToInt(Student::getScore).forEach(System.out::println);
        System.out.println("---------------------------");

        IntStream intStream1 = Arrays.stream(intArray);
        intStream1.asDoubleStream().forEach(System.out::println);
        System.out.println("---------------------------");

        IntStream intStream2 = Arrays.stream(intArray);
        intStream2.boxed().forEach(System.out::println);

    }
}
