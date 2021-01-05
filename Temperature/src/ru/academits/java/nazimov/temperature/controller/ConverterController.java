package ru.academits.java.nazimov.temperature.controller;

import ru.academits.java.nazimov.temperature.model.*;

public class ConverterController implements Controller {
    private final Model model;

    public ConverterController(Model model) {
        this.model = model;
    }

    public double convertTemperature(double temperature, String fromTemperature, String toTemperature) {
        return model.convert(temperature, fromTemperature, toTemperature);
    }
}
