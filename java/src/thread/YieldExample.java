package thread;

public class YieldExample {
    public static void main(String[] args) {
        WorkThread wordThreadA = new WorkThread("wordThreadA");
        WorkThread wordThreadB = new WorkThread("wordThreadB");

        wordThreadA.start();
        wordThreadB.start();

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){}
        wordThreadA.work = false;

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){}
        wordThreadA.work = true;
    }
}
