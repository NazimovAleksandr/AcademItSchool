package ru.academits.java.nazimov.temperature.model;

public class TemperatureConverter {
    public static final String CELSIUS = "Цельсия";
    public static final String FAHRENHEIT = "Фаренгейта";
    public static final String KELVIN = "Кельвин";

    private final String[] comboBoxTemperatures = {CELSIUS, FAHRENHEIT, KELVIN};

    private double temperature;

    public String[] getComboBox() {
        return comboBoxTemperatures;
    }

    public double getTemperature() {
        return temperature;
    }

    public void celsiusTo(double temperature, String scale) {
        switch (scale) {
            case (FAHRENHEIT) -> this.temperature = temperature * 9 / 5 + 32;
            case (KELVIN) -> this.temperature = temperature + 273.15;
            default -> this.temperature = temperature;
        }
    }

    public void fahrenheitTo(double temperature, String scale) {
        switch (scale) {
            case (KELVIN) -> this.temperature = (temperature - 32) * 5 / 9 + 273.15;
            case (CELSIUS) -> this.temperature = (temperature - 32) * 5 / 9;
            default -> this.temperature = temperature;
        }
    }

    public void kelvinTo(double temperature, String scale) {
        switch (scale) {
            case (FAHRENHEIT) -> this.temperature = (temperature - 273.15) * 9 / 5 + 32;
            case (CELSIUS) -> this.temperature = temperature - 273.15;
            default -> this.temperature = temperature;
        }
    }
}
