package basic_core.genericity._class;

// 泛型类，泛型容器，可以存任意类型
public class GenericContainer<T> {

    private T obj;

    public GenericContainer () {
    }

    public GenericContainer(T t) {
        this.obj = t;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
