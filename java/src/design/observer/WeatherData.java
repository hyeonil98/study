package design.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
    // Observer 객체 저장
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        // 옵저버들에게 전파
        notifyObserver();
    }

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // 모든 옵저버에게 상태 변화를 알려줌
    @Override
    public void notifyObserver() {
        observers.forEach(observer -> {
            observer.update(temperature, humidity, pressure);
        });
    }

    public void setMeasurements(float temperature,  float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();

    }
}
