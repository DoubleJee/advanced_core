package jvm.gc;


class Test{
    //被回收之前调用的方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println("我要被GC回收了");
    }
}

//GC垃圾回收机制：不定时的向堆内存清理不可达对象。不可达对象不一定会被马上收回，没有被引用的对象，（没有一个变量存储对象的内存地址，没有被引用）
public class GcTest {

    public static void main(String[] args) {
        //不可达对象 不被经常使用，没有被引用的
        Test test = new Test();
        test = null;
        //手动回收垃圾 通知gc可以进行扫描回收垃圾了
        System.gc();
    }

}
