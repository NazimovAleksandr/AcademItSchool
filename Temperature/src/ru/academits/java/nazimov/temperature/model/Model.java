package ru.academits.java.nazimov.temperature.model;

public interface Model {
    String[] getTemperatures();

    double convert(double temperature, String fromTemperature, String toTemperature);
}
