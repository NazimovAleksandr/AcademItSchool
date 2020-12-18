package ru.academits.java.nazimov.temperature_main;

import ru.academits.java.nazimov.temperature.controller.Controller;
import ru.academits.java.nazimov.temperature.model.TemperatureConverter;
import ru.academits.java.nazimov.temperature.view.DesktopView;
import ru.academits.java.nazimov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter model = new TemperatureConverter();
        Controller controller = new Controller(model);
        View view = new DesktopView(model, controller);

        view.start();
    }
}
