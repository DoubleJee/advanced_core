package jvm.optimize;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class HeapOverFlow implements OverFlow{

    //Xms10m -Xmx10m 抛出堆异常  对象占用内存大于最大堆内存，解决：增加堆内存大小
    @Override
    public void overFlow() {
        List<Object> objectList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++){
            objectList.add(new byte[1 * 1024 * 1024]);
        }
        System.out.println("装载成功");
    }

    public static void main(String[] args) {
        OverFlow overFlow = new HeapOverFlow();
        overFlow.overFlow();
    }
}

class StackOverFlow implements OverFlow{

    private static int count = 0;

    //方法里递归方法，栈内存一直叠加无法释放。增加栈深度，去掉无限递归
    @Override
    public void overFlow() {
        try {
            count++;
            overFlow();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("堆深度：" + count);
        }
    }

    //循环调用方法不会出现，因为方法执行完释放，再执行再释放
    public static void main(String[] args) {
        OverFlow overFlow = new StackOverFlow();
        overFlow.overFlow();
    }
}


class MethodRegionOverFlow implements OverFlow{

    //-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m 抛出OOM  创建了大量类型，方法区要存储大量动态创建加载的类型信息导致内存溢出 解决：关注运行时生成大量动态类的场景。
    @Override
    public void overFlow() {
        while (true){
            //cglib 生产大量动态类
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodRegionOverFlow.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });
            //动态生成新的代理类
            enhancer.create();
        }
    }

    public static void main(String[] args) {
        OverFlow overFlow = new MethodRegionOverFlow();
        overFlow.overFlow();
    }
}


public interface OverFlow {
    void overFlow();
}
