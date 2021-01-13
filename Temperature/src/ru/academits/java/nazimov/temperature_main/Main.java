package ru.academits.java.nazimov.temperature_main;

import ru.academits.java.nazimov.temperature.model.*;
import ru.academits.java.nazimov.temperature.view.*;

public class Main {
    public static void main(String[] args) {
        Model model = new TemperatureConverter();
        View view = new DesktopView(model);

        view.start();
    }
}