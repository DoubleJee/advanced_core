package design_pattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton);
                }
            });
        }
        //单例模式：确保一个类只有一个实例，并提供全局访问点。
    }
}
