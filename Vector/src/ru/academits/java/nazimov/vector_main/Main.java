package ru.academits.java.nazimov.vector_main;

import ru.academits.java.nazimov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {3, 5, 7};

        Vector vector1 = new Vector(array);

        Vector vector2 = new Vector(6, array);

        System.out.println("Vector1 = " + vector1);
        System.out.println("Vector2 = " + vector2);
        System.out.println();

        vector2.setComponent(5, 6);
        System.out.println("Vector.setComponent");
        System.out.println("Vector2 = " + vector2);
        System.out.println();

        System.out.println("Vector2.length = " + vector2.getLength());
        System.out.println();

        Vector vector3 = new Vector(vector1);

        System.out.println("Vector3 = " + vector3);
        System.out.println("Vector3, index 2 = " + vector3.getComponent(2));
        System.out.println("Vector3.getSize = " + vector3.getSize());
        System.out.println();

        vector3.setComponent(2, 33);
        vector1.add(vector3);
        System.out.println("Vector1 + Vector3 = " + vector1);
        System.out.println("(Static method) Vector1 + Vector2 = " + Vector.getSum(vector1, vector2));
        System.out.println();

        vector3.subtract(vector1);
        System.out.println("Vector3 - Vector1 = " + vector3);
        System.out.println("(Static method) Vector1 - Vector2 = " + Vector.getDifference(vector2, vector1));
        System.out.println();

        vector3.multiplyByNumber(4);
        System.out.println("Vector3 * Scalar(4) = " + vector3);
        System.out.println("(Static method) Vector1 * Vector2 = " + Vector.getScalarProduct(vector1, vector2));
        System.out.println();

        System.out.println("Vector1 = " + vector1);
        vector1.revert();
        System.out.println("Revert Vector1 = " + vector1);
    }
}
