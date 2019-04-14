package design_pattern.observer.inlay;

import design_pattern.observer.DisplayElement;
import design_pattern.observer.subject.Subject;

import java.util.Observable;
import java.util.Observer;

public class HighTemperatureDisplay implements Observer, DisplayElement {

    private Observable observable;
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

    public HighTemperatureDisplay(Observable observable){
        this.observable = observable;
        this.observable.addObserver(this);
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
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }
}
