package design_pattern.observer.observer.impl;

import design_pattern.observer.DisplayElement;
import design_pattern.observer.observer.Observer;
import design_pattern.observer.subject.Subject;

public class CurrentConditionDisplay implements Observer, DisplayElement {

    private Subject subject;

    /**
     * 温度
     */
    private float temperature;

    /**
     * 湿度
     */
    private float humidity;

    /**
     * 压力
     */
    private float pressure;

    public CurrentConditionDisplay(Subject subject){
        this.subject = subject;
        //向主题注册自己
        this.subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("当前湿度：" + temperature + "F，当前温度：" + humidity + "% " + "，当前压力值" + pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void exitJt(){
        this.subject.removeObserver(this);
    }
}
