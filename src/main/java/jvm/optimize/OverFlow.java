package jvm.optimize;

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


public interface OverFlow {
    void overFlow();
}
