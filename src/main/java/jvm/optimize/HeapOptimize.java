package jvm.optimize;


//堆内存调优，堆初始化大小必须和堆最大值一致，减少垃圾回收次数，初始值越小，垃圾回收次数越多
public class HeapOptimize {

    //初始堆大小30m、最大堆30m、新生代和老年代 1:2、打印GC回收日志详情、GC串行回收、打印命令行标志
    // -Xms30m -Xmx30m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
    public static void main(String[] args) {
        byte[] m5 = new byte[5 * 1024 * 1024];
        System.out.println("分配了堆5M内存");
        jvmInfo();
        byte[] m10 = new byte[10 * 1024 * 1024];
        System.out.println("分配了堆10M内存");
        jvmInfo();

    }

    /**
     * 打印内存情况
     */
    static void jvmInfo() {
        //运行时java应用实例
        Runtime runtime = Runtime.getRuntime();
        //最大内存
        long maxMemory = runtime.maxMemory();
        //空闲内容
        long freeMemory = runtime.freeMemory();
        //已用内存
        long totalMemory = runtime.totalMemory();
        System.out.println("最大内存：" + maxMemory / 1024l + "kb," + maxMemory / 1024l / 1024l + "m");
        System.out.println("可用内存：" + freeMemory / 1024l + "kb," + freeMemory / 1024l / 1024l+ "m");
        System.out.println("已用内存：" + totalMemory / 1024l + "kb," + totalMemory / 1024l / 1024l+ "m");

    }
}
