package lambda;

public class ButtonExample {
    public static void main(String[] args) {
        Button button = new Button();

        button.setClickListener(() -> {
            System.out.println("Ok 버튼을 클릭했습니다");
        });

        button.click();
    }
}
