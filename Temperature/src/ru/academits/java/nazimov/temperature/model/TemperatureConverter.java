package ru.academits.java.nazimov.temperature.model;

import ru.academits.java.nazimov.temperature.model.scales.TemperatureScale;

public interface TemperatureConverter {
    TemperatureScale[] getTemperatureScales();

    double convert(double temperature, TemperatureScale fromScale, TemperatureScale toScale);
}