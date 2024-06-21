package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingExample {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            studentList.add(new Student("Person"+(i+1), i*10));
        }

        // 오름차순 정렬
        studentList.stream()
                .sorted()
                .forEach(s -> System.out.println(s.getName() + ": "+ s.getScore()));

        System.out.println("----------------------");

        // 내림차순 정렬
        studentList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.println(s.getName() + ": "+ s.getScore()));
    }
}
