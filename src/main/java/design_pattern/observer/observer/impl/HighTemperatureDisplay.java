package design_pattern.observer.observer.impl;

import design_pattern.observer.DisplayElement;
import design_pattern.observer.observer.Observer;
import design_pattern.observer.subject.Subject;

public class HighTemperatureDisplay implements Observer, DisplayElement {
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

    public HighTemperatureDisplay(Subject subject){
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void display() {
        if (humidity > 25){
            System.out.println("高温预警！！！" + humidity + "%");
            return;
        }
        System.out.println("温度正常，" + humidity + "%");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
