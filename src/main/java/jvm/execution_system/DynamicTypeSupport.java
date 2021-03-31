package jvm.execution_system;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

// Java对动态类型的支持
public class DynamicTypeSupport {


    public static void main(String[] args) throws Throwable {

        // 运行时决定实际类型
        Object object = System.currentTimeMillis() % 2 == 0 ? System.out : new MyPrintln();

        // 获得方法句柄，动态调用支持，确定分派版本
        MethodHandle printlnMH = getPrintlnMH(object);
        // 精确执行方法句柄
        printlnMH.invokeExact("hello gzz");


    }


    // 分派逻辑 用户自己实现
    public static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        // 方法类型 第一个参数返回值，第二个已至后面的是参数值
        MethodType mt = MethodType.methodType(void.class,String.class);

        // 方法句柄工具类，lookup查找给定类的指定方法简单名称和方法类型，而且符合调用权限的虚方法句柄，bindTo绑定调用接受者，并传递自身引用过去，（Java虚拟机内部是放在局部变量表槽0的位置）
        return MethodHandles.lookup().findVirtual(reveiver.getClass(),"println",mt).bindTo(reveiver);
    }

    static class MyPrintln{

        public void println(String val){
            System.err.println("MyPrintln print:" + val);
        }


    }
}
