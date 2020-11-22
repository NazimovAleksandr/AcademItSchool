package ru.academits.java.nazimov.vector;

public class Vector {
    private final double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Некорректный размер");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, this.components, 0, vector.components.length);
    }

    public Vector(double[] array) {
        components = new double[array.length];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public Vector(int size, double[] array) {
        if (size < array.length) {
            throw new IllegalArgumentException("Некорректный размер");
        }

        components = new double[size];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public int getSize() {
        return components.length;
    }

    public Vector getVectorsSum(Vector vector) {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException("Некорректный размер, размеры Векторов должны быть одинаковыми");
        }

        Vector vectorSum = new Vector(vector.components.length);

        for (int i = 0; i < components.length; i++) {
            vectorSum.components[i] = components[i] + vector.components[i];
        }

        return vectorSum;
    }

    public Vector getVectorsDifference(Vector vector) {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException("Некорректный размер, размеры Векторов должны быть одинаковыми");
        }

        Vector vectorDifference = new Vector(vector.components.length);

        for (int i = 0; i < components.length; i++) {
            vectorDifference.components[i] = components[i] - vector.components[i];
        }

        return vectorDifference;
    }

    public Vector getScalarMultiplication(double scalar) {
        Vector multiplicationResult = new Vector(components.length);

        for (int i = 0; i < components.length; i++) {
            multiplicationResult.components[i] = components[i] * scalar;
        }

        return multiplicationResult;
    }

    public Vector getRevertVector() {
        Vector revertVector = new Vector(components.length);

        for (int i = 0; i < components.length; i++) {
            revertVector.components[i] = components[i] * -1;
        }

        return revertVector;
    }

    public double getVectorLength() {
        double result = 0;

        for (double e : components) {
            result += Math.pow(e, 2);
        }

        return Math.abs(Math.sqrt(result));
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным и больше размера вектора");
        }

        return components[index];
    }

    public void setComponent(double component, int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным и больше размера вектора");
        }

        components[index] = component;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        for (int i = 0; i < components.length; i++) {
            stringBuilder.append(components[i]);

            if (i == components.length - 1) {
                break;
            }

            stringBuilder.append(", ");
        }

        stringBuilder.append(" }");
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
        int hash = 1;

        for (double component : components) {
            hash += Double.hashCode(component);
        }

        return hash;
    }

    public static Vector getVectorsSum(Vector v1, Vector v2) {
        Vector result = new Vector(Math.max(v1.components.length, v2.components.length), v1.components);

        for (int i = 0; i < result.components.length; i++) {
            if (i < v2.components.length) {
                result.components[i] += v2.components[i];
            }
        }

        return result;
    }

    public static Vector getVectorsDifference(Vector v1, Vector v2) {
        Vector result = new Vector(Math.max(v1.components.length, v2.components.length), v1.components);

        for (int i = 0; i < result.components.length; i++) {
            if (i < v2.components.length) {
                result.components[i] -= v2.components[i];
            }
        }

        return result;
    }

    public static double getScalarVectorsMultiplication(Vector v1, Vector v2) {
        double result = 0;

        for (int i = 0; i < v1.components.length; i++) {
            result += v1.components[i] * v2.components[i];
        }

        return result;
    }
}
