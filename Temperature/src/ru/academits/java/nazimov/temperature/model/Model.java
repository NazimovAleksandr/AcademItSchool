package ru.academits.java.nazimov.temperature.model;

import ru.academits.java.nazimov.temperature.model.scales.Scale;

public interface Model {
    Scale[] getScale();

    double convert(double temperature, Scale fromTemperature, Scale toTemperature);
}