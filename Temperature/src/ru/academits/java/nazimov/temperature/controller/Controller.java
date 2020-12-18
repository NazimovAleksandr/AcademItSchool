package ru.academits.java.nazimov.temperature.controller;

import ru.academits.java.nazimov.temperature.model.TemperatureConverter;

public class Controller {
    private final TemperatureConverter model;


    public Controller(TemperatureConverter model) {
        this.model = model;
    }

    public void convertTemperature(double temperature, String fromTemperature, String toTemperature) {
        switch (fromTemperature) {
            case (TemperatureConverter.KELVIN) -> model.kelvinTo(temperature, toTemperature);
            case (TemperatureConverter.FAHRENHEIT) -> model.fahrenheitTo(temperature, toTemperature);
            case (TemperatureConverter.CELSIUS) -> model.celsiusTo(temperature, toTemperature);
        }
    }
}
