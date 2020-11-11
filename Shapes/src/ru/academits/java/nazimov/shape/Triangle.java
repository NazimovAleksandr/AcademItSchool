package ru.academits.java.nazimov.shape;

import java.util.Objects;

public final class Triangle implements Shape {
    private final double x1;
    private final double y1;

    private final double x2;
    private final double y2;

    private final double x3;
    private final double y3;

    private static final double EPSILON = 1.0e-10;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= EPSILON) {
            throw new IllegalArgumentException("Введены некорректные данные, точки лежат на одной прямой.");
        }

        this.x1 = x1;
        this.y1 = y1;

        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;
    }

    public double getSideA() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getSideB() {
        return Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }

    public double getSideC() {
        return Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    @Override
    public double getWidth() {
        double xMax = Math.max(x1, x2);
        xMax = Math.max(xMax, x3);

        double xMin = Math.min(x1, x2);
        xMin = Math.min(xMin, x3);

        return xMax - xMin;
    }

    @Override
    public double getHeight() {
        double yMax = Math.max(y1, y2);
        yMax = Math.max(yMax, y3);

        double yMin = Math.min(y1, y2);
        yMin = Math.min(yMin, y3);

        return yMax - yMin;
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - getSideA()) *
                (halfPerimeter - getSideB()) * (halfPerimeter - getSideC()));
    }

    @Override
    public double getPerimeter() {
        return getSideA() + getSideB() + getSideC();
    }

    @Override
    public String toString() {
        return "Triangle { x1=" + x1 + ", y1=" + y1 +
                ", x2=" + x2 + ", y2=" + y2 +
                ", x3=" + x3 + ", y3=" + y3 + "}";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) object;

        return Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
