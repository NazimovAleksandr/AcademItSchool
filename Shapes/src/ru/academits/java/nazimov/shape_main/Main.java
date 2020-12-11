package ru.academits.java.nazimov.shape_main;

import ru.academits.java.nazimov.shape.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(5),
                new Triangle(5, 2, 8, 5, 4, 7),
                new Rectangle(5, 10),
                new Circle(10),
                new Square(13),
                new Triangle(4, 3, 7, 6, 2, 4),
                new Rectangle(8, 4),
                new Circle(4)
        };

        Shape shapeWitMaxArea = getShapeWithMaxArea(shapes);

        if (shapeWitMaxArea != null) {
            System.out.printf("Shape: %s%n Width: %.2f%n Height: %.2f%n Area: %.2f%n Perimeter: %.2f%n",
                    shapeWitMaxArea, shapeWitMaxArea.getWidth(), shapeWitMaxArea.getHeight(),
                    shapeWitMaxArea.getArea(), shapeWitMaxArea.getPerimeter());
            System.out.println();
        }

        Shape shapeWithSecondPerimeter = getShapeWithSecondLargestPerimeter(shapes);

        if (shapeWithSecondPerimeter != null) {
            System.out.printf("Shape: %s%n Width: %.2f%n Height: %.2f%n Area: %.2f%n Perimeter: %.2f%n",
                    shapeWithSecondPerimeter, shapeWithSecondPerimeter.getWidth(), shapeWithSecondPerimeter.getHeight(),
                    shapeWithSecondPerimeter.getArea(), shapeWithSecondPerimeter.getPerimeter());
        }

        System.out.println("Задача засчитана");
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes.length == 0 || shapes.length == 1) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }
}
