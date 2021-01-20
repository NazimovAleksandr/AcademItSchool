package ru.academits.java.nazimov.temperature.model.scales;

public interface TemperatureScale {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}