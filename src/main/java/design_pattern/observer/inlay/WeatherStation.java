package design_pattern.observer.inlay;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        HighTemperatureDisplay highTemperatureDisplay = new HighTemperatureDisplay(weatherData);
        weatherData.setMeasurements(90,23,13.4f);
        weatherData.setMeasurements(82,37,14.4f);
        weatherData.setMeasurements(73,40,15.4f);
    }
}
