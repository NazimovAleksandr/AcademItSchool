package ru.academits.java.nazimov.shape_main;

import ru.academits.java.nazimov.shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Square(5);
        shapes[1] = new Triangle(5, 2, 8, 5, 4, 7);
        shapes[2] = new Rectangle(5, 10);
        shapes[3] = new Circle(10);
        shapes[4] = new Square(13);
        shapes[5] = new Triangle(4, 3, 7, 6, 2, 4);
        shapes[6] = new Rectangle(8, 4);
        shapes[7] = new Circle(4);

        Shape maxArea = getAreaMax(shapes);
        System.out.printf("Shape: %s%n Width: %.2f%n Height: %.2f%n Area: %.2f%n Perimeter: %.2f%n Area: %.2f%n",
                maxArea.getClass(), maxArea.getWidth(), maxArea.getHeight(),
                maxArea.getArea(), maxArea.getPerimeter(), maxArea.getArea());

        System.out.println();

        Shape secondPerimeter = getSecondLargestPerimeter(shapes);
        System.out.printf("Shape: %s%n Width: %.2f%n Height: %.2f%n Area: %.2f%n Perimeter: %.2f%n Area: %.2f%n",
                secondPerimeter.getClass(), secondPerimeter.getWidth(), secondPerimeter.getHeight(),
                secondPerimeter.getArea(), secondPerimeter.getPerimeter(), secondPerimeter.getArea());
    }

    public static Shape getAreaMax(Shape[] shapes) {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return (int) (o2.getArea() - o1.getArea());
            }
        });

        return shapes[0];
    }

    public static Shape getSecondLargestPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return (int) (o2.getPerimeter() - o1.getPerimeter());
            }
        });

        return shapes[1];
    }
}
