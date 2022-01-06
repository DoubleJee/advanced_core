package basic_core.genericity._class;

public class TestClass {

    public static void main(String[] args) {
        generic();
        bounded();
    }


    // 泛型
    static void generic() {
        GenericContainer<Integer> myInt = new GenericContainer<>();
        myInt.setObj(10); // 正确
        //  myInt.setObj("100"); // 不能编译


        // 多个泛型
        MultiGenericContainer<Integer, Double> dayOfWeekDegrees = new MultiGenericContainer<>(1, 78.0);
        Integer day = dayOfWeekDegrees.getFirstPosition(); // 天
        Double degrees = dayOfWeekDegrees.getSecondPosition(); // 度数
    }


    // 有界泛型
    static void bounded() {
        GenericNumberContainer<Integer> gn = new GenericNumberContainer<>();
        gn.setObj(3);

        // 无法编译，String不是Number的子类，有界
        // GenericNumberContainer<String> gs = new GenericNumberContainer<String>();
    }
}
