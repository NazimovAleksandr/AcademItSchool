package ru.academits.java.nazimov.temperature.model.scales;

public class Celsius implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Цельсия";
    }
}