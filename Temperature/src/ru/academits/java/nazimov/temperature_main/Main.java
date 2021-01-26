package ru.academits.java.nazimov.temperature_main;

import ru.academits.java.nazimov.temperature.model.*;
import ru.academits.java.nazimov.temperature.model.scales.*;
import ru.academits.java.nazimov.temperature.view.*;

public class Main {
    public static void main(String[] args) {
        TemperatureScale[] scales = {
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        };

        TemperatureConverter converter = new Converter(scales);
        View view = new DesktopView(converter);

        view.start();
    }
}