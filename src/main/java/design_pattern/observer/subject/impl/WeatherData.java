package design_pattern.observer.subject.impl;

import design_pattern.observer.observer.Observer;
import design_pattern.observer.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    /**
     * 观察者列表
     */
    private List<Observer> observers;

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

    public WeatherData(){
        observers = new ArrayList<>();
    }



    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature,humidity,pressure);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    /**
     * 更新测量值
     */
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
