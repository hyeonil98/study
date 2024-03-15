package generic;

/*
    제너릭(Generic)은 결정되지 않은 타입에 대해 파라미터 T 로 처리하고,
    실제 사용되는 파라미터를 구체적인 타입으로 대체시킨다.

 */
public class Box <T>{
    public T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
