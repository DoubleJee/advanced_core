package design_pattern.observer.observer;

public interface Observer {
    //接收通知
    void update(float temp,float humidity,float pressure);
}
