package ru.academits.java.nazimov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер должен быть положительным числом и больше 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, components, 0, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Нельзя передавать пустой массив");
        }

        components = new double[array.length];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер должен быть положительным числом и больше 0");
        }

        components = new double[Math.max(size, array.length)];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public int getSize() {
        return components.length;
    }

    public void getSum(Vector vector) {
        if (vector.components.length > components.length) {
            double[] newComponents = new double[vector.components.length];

            System.arraycopy(components, 0, newComponents, 0, components.length);

            components = newComponents;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void getDifference(Vector vector) {
        if (vector.components.length > components.length) {
            double[] newComponents = new double[vector.components.length];

            System.arraycopy(components, 0, newComponents, 0, components.length);

            components = newComponents;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void getMultiplyByNumber(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void getRevertVector() {
        getMultiplyByNumber(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += Math.pow(e, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Неверный индекс " + index
                    + ", допустимые значения: от 0 до " + (getSize() - 1));
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Неверный индекс " + index
                    + ", допустимые значения: от 0 до " + (getSize() - 1));
        }

        components[index] = component;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        for (double e : components) {
            stringBuilder.append(e).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (components.length != vector.components.length) {
            return false;
        }

        for (int i = 0; i < components.length; i++) {
            if (components[i] != vector.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int vectorSize = Math.max(vector1.components.length, vector2.components.length);

        Vector result = new Vector(vectorSize, vector1.components);
        Vector vector = new Vector(vectorSize, vector2.components);

        result.getSum(vector);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int vectorSize = Math.max(vector1.components.length, vector2.components.length);

        Vector result = new Vector(vectorSize, vector1.components);
        Vector vector = new Vector(vectorSize, vector2.components);

        result.getDifference(vector);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int vectorSize = Math.max(vector1.components.length, vector2.components.length);

        Vector first = new Vector(vectorSize, vector1.components);
        Vector second = new Vector(vectorSize, vector2.components);

        double result = 0;

        for (int i = 0; i < vectorSize; i++) {
            result += first.components[i] * second.components[i];
        }

        return result;
    }
}
