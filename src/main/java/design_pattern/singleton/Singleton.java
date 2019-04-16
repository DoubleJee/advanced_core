package design_pattern.singleton;

public class Singleton {

    private static Singleton singleton;

    //私有构造函数
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
