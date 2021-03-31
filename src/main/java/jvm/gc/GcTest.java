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

class LocalSotGC {


    // 局部变量表槽复用对GC的影响
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        // 离开了作用域，因为槽复用，placeholder还在指向64M的大小的对象

        // 因为槽复用，placeholder所在的槽，被替换给a变量使用
        int a = 0;
        // 这时候gc，64M对象没有被局部变量槽所引用指向，便回收了
        System.gc();
    }


}