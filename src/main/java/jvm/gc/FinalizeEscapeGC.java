package jvm.gc;

//对象逃脱GC，（
//
//            GC标记了不可达对象之后，会进行筛选，将重写了finalize方法，并且被没虚拟机调用过finalize方法的对象，放入FQueue。 PS：这种情况都被视为 “有必要执行”
//            接下来虚拟机将会自动建立finalizer线程(优先级很低)，去执行FQueue队列中对象的finalize方法，这是对象唯一的死亡逃脱机会。
//            但并不一定保证方法一定执行结束，
//            因为可能有些对象的finalize方法可能会执行缓慢，占用大量时间，或死循环造成整个队列的其他对象永远处于等待状态，会造成整个内存回收子系统进而崩溃）
//
//
//            对象要成功逃脱，只需要重写finalize方法，重新将自己被GC roots引用链中的任何一个对象建立关联，比如把自身this关键字 赋值给某个类变量或者对象的成员变量，
//
//            接下来会有第二次标记，它会被移除 “即将回收”的集合，如果这个时候对象还没逃脱，那么它基本上是要被回收了。
//
//
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("我还活着!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("我在自救");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) {
        SAVE_HOOK = new FinalizeEscapeGC();


        //通知GC垃圾回收，对象第一次自救
        SAVE_HOOK = null;
        System.gc();
        //Finalizer线程优先级很低，等待0.5s，以等待它
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("对象已经死了");
        }



        //第二次 此对象已经被虚拟机线程执行过一次了，不会再走finalize方法。没法自救了
        SAVE_HOOK = null;
        System.gc();
        //Finalizer线程优先级很低，等待0.5s，以等待它
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("对象已经死了");
        }
    }
}
