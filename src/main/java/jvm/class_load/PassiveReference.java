package jvm.class_load;
//被动引用的几种场景情况
public class PassiveReference {

    public static void main(String[] args) {
        //情况1
//        System.out.println(SubClass.value);

        //情况2：通过数组定义来引用类，不会触发此类的初始化
//        SuperClass[] superClasses = new SuperClass[10];

        //情况3
        System.out.println(SuperClass.HELLO_WORD);

    }

}

/**
 * 情况1：通过子类引用父类的静态字段，不会导致子类初始化，
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，
 * 通过子类来调用父类中定义的静态字段，只会触发父类初始化
 */
class SuperClass{

    public static int value = 123;

    //情况3：常量在编译阶段会存入调用类（PassiveReference）的常量池中，本质上没有直接引用定义常量的类，用的自身常量池里的引用，因此不会触发定义常量的类的初始化
    public static final String HELLO_WORD = "hello word";

    static {
        System.out.println("父类初始化！");
    }
}
class SubClass extends SuperClass{
    static {
        System.out.println("子类初始化！");
    }
}