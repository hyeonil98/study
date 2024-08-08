package design.observer;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 52, 30.4F);
        weatherData.setMeasurements(40, 51, 20.7F);
        weatherData.setMeasurements(50, 56, 10.9F);

    }
}
