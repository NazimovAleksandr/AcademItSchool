package ru.academits.java.nazimov.temperature.model.scales;

public interface Scale {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}