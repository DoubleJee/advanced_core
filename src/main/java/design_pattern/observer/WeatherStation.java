package design_pattern.observer;

import design_pattern.observer.observer.impl.CurrentConditionDisplay;
import design_pattern.observer.observer.impl.HighTemperatureDisplay;
import design_pattern.observer.subject.impl.WeatherData;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        HighTemperatureDisplay highTemperatureDisplay = new HighTemperatureDisplay(weatherData);
        weatherData.setMeasurements(90,23,13.4f);
        weatherData.setMeasurements(82,37,14.4f);
        currentConditionDisplay.exitJt();
        weatherData.setMeasurements(73,40,15.4f);

        //观察者模式：在对象之间定义一个一对多的依赖，当一个对象改变状态的时候，所有依赖他的对象都会收到通知，并自动更新。
    }
}
