package ru.academits.java.nazimov.temperature.model;

import ru.academits.java.nazimov.temperature.model.scales.Celsius;
import ru.academits.java.nazimov.temperature.model.scales.Fahrenheit;
import ru.academits.java.nazimov.temperature.model.scales.Kelvin;
import ru.academits.java.nazimov.temperature.model.scales.Scale;

public class TemperatureConverter implements Model {
    private final Scale[] scales = {new Celsius(), new Fahrenheit(), new Kelvin()};

    public Scale[] getScale() {
        return scales;
    }

    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(temperature));
    }
}