package ru.academits.java.nazimov.temperature.model.scales;

public class Kelvin implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}