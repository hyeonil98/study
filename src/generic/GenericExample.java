package generic;

public class GenericExample {
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<>();
        box.setContent(t);
        return box;
    }

    // 제한된 타입 파라미터
    public static <T extends Number> boolean compare(T t1, T t2) {
        return t1.doubleValue() == t2.doubleValue();
    }
    public static void main(String[] args) {
        // 생성자 타입으로 자동 변환
        Box<String> box1 = new Box<>();
        box1.content = "안녕하세요";
        System.out.println("box1.getContent() = " + box1.getContent());
        
        Box<Integer> box2 = new Box<>();
        box2.content = 23;
        System.out.println("box2 = " + box2.getContent());

        System.out.println("===========================");
        
        Product<Tv, String> product1 = new Product<>();
        
        // 반드시 Tv 타입이 들어가야함
        product1.setKind(new Tv());
        product1.setModel("스마트 TV");
        
        Product<Car, String> car1 = new Product<>();
        
        // 반드시 Car 타입이 들어가야함
        car1.setKind(new Car());
        car1.setModel("세단");

        System.out.println("product1.toString() = " + product1.toString());
        System.out.println("car1.toString() = " + car1.toString());
        System.out.println("===========================");

        HomeAgency homeAgency = new HomeAgency();
        Home rent = homeAgency.rent();
        rent.turnOnLight();

        CarAgency carAgency = new CarAgency();
        Car car = carAgency.rent();
        car.run();
        System.out.println("===========================");

        Box<Integer> box3 = boxing(100);
        System.out.println("box3.getContent() = " + box3.getContent());

        Box<String> box4 = boxing("String");
        System.out.println("box4.getContent() = " + box4.getContent());
        System.out.println("===========================");

        Cource.registerCourse1(new Applicant<Person>(new Person()));
        Cource.registerCourse1(new Applicant<Worker>(new Worker()));
        Cource.registerCourse1(new Applicant<Student>(new Student()));
        Cource.registerCourse1(new Applicant<HighStudent>(new HighStudent()));
        Cource.registerCourse1(new Applicant<MiddleStudent>(new MiddleStudent()));

//        Cource.registerCourse2(new Applicant<Person>(new Person()));
//        Cource.registerCourse2(new Applicant<Worker>(new Worker()));
        Cource.registerCourse2(new Applicant<Student>(new Student()));
        Cource.registerCourse2(new Applicant<HighStudent>(new HighStudent()));
        Cource.registerCourse2(new Applicant<MiddleStudent>(new MiddleStudent()));

        Cource.registerCourse3(new Applicant<Person>(new Person()));
        Cource.registerCourse3(new Applicant<Worker>(new Worker()));
//        Cource.registerCourse3(new Applicant<Student>(new Student()));
//        Cource.registerCourse3(new Applicant<HighStudent>(new HighStudent()));
//        Cource.registerCourse3(new Applicant<MiddleStudent>(new MiddleStudent()));




    }
}
