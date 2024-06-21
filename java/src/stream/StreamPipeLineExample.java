package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.round;

public class StreamPipeLineExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("Person1", 10),
                new Student("Person2", 20),
                new Student("Person3", 50)
        );
        double avg = list.stream()
                .mapToInt(Student::getScore)
                .average()
                .getAsDouble();

        System.out.println("avg = " + round(avg*100)/100.0);
    }
    
}
