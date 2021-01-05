package ru.academits.java.nazimov.temperature.model;

public class TemperatureConverter implements Model {
    private static final String CELSIUS = "Цельсия";
    private static final String FAHRENHEIT = "Фаренгейта";
    private static final String KELVIN = "Кельвин";

    private final String[] temperatures = {CELSIUS, FAHRENHEIT, KELVIN};

    private double temperature;

    public String[] getTemperatures() {
        return temperatures;
    }

    public double convert(double temperature, String fromTemperature, String toTemperature) {
        switch (fromTemperature) {
            case (CELSIUS) -> convertFromCelsius(temperature, toTemperature);
            case (FAHRENHEIT) -> convertFromFahrenheit(temperature, toTemperature);
            case (KELVIN) -> convertFromKelvin(temperature, toTemperature);
        }

        return this.temperature;
    }

    private void convertFromCelsius(double temperature, String scale) {
        switch (scale) {
            case (FAHRENHEIT) -> this.temperature = temperature * 9 / 5 + 32;
            case (KELVIN) -> this.temperature = temperature + 273.15;
            default -> this.temperature = temperature;
        }
    }

    private void convertFromFahrenheit(double temperature, String scale) {
        switch (scale) {
            case (KELVIN) -> this.temperature = (temperature - 32) * 5 / 9 + 273.15;
            case (CELSIUS) -> this.temperature = (temperature - 32) * 5 / 9;
            default -> this.temperature = temperature;
        }
    }

    private void convertFromKelvin(double temperature, String scale) {
        switch (scale) {
            case (FAHRENHEIT) -> this.temperature = (temperature - 273.15) * 9 / 5 + 32;
            case (CELSIUS) -> this.temperature = temperature - 273.15;
            default -> this.temperature = temperature;
        }
    }
}
