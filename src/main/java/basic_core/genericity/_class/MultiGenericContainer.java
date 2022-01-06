package basic_core.genericity._class;
// 多泛型类，多路泛型容器，可以存两个任意类型
public class MultiGenericContainer<T, S> {

    private T firstPosition;
    private S secondPosition;

    public MultiGenericContainer(T firstPosition, S secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public T getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(T firstPosition) {
        this.firstPosition = firstPosition;
    }

    public S getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(S secondPosition) {
        this.secondPosition = secondPosition;
    }
}
