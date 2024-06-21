package lambda;

public class LambdaExample {
    public static void action(Calculable calculable) {
        int x = 10;
        int y = 4;
        calculable.calculate(x, y);
    }

    public static void main(String[] args) {
        action((x, y) -> {
            int result = x + y;
            System.out.println("result : "+result);
        });

        action((x, y) -> {
            int result = x - y;
            System.out.println("result : "+result);
        });

        Person person = new Person();
        person.action(() -> {
            System.out.println("출근을 합니다");
            System.out.println("프로그래밍을 합니다");
        });

        person.action(() -> {
            System.out.println("퇴근합니다");
        });

    }
}
