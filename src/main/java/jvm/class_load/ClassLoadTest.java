package jvm.class_load;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //类加载器 通过全限定名获取该类的二进制字节流，如何获取，去实现的就是类加载器
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                //确定class文件名
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                //读取当前目录下class文件的字节流
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null){
                    //默认引导类加载器（应用程序类加载器）
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    //字节流转换为方法区运行时结构，并在Java堆中创建对应的java.lang.Class对象，作为对方法区类结构的访问
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new ClassNotFoundException(name);
            }
        };
        Object o = classLoader.loadClass("jvm.class_load.ClassLoadTest").newInstance();
        System.out.println(o);
        System.out.println(o.getClass());
        //同一个class文件但是不同类加载器加载出来，永远不相等，必须同一个类加载器
        System.out.println(o instanceof ClassLoadTest);
    }
}
