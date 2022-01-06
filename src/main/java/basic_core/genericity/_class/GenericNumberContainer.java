package basic_core.genericity._class;

// 有界泛型，数字泛型容器，只接受Number的子类类型
public class GenericNumberContainer<T extends Number> {

    private T obj;

    public GenericNumberContainer () {
    }

    public GenericNumberContainer(T t) {
        this.obj = t;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
