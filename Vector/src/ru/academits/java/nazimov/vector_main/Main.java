package ru.academits.java.nazimov.vector_main;

import ru.academits.java.nazimov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {3, 5, 7};

        Vector vector1 = new Vector(array);

        Vector vector2 = new Vector(6, array);

        System.out.println("Vector1 = " + vector1);
        System.out.println("Vector2 = " + vector2);

        vector2.setComponent(6, 5);
        System.out.println("Vector2 = " + vector2);
        System.out.println("Vector2.length = " + vector2.getVectorLength());

        Vector vector3 = new Vector(vector1);

        System.out.println("Vector3 = " + vector3);
        System.out.println("Vector3, index 2 = " + vector3.getComponent(2));
        System.out.println("Vector3.getSize = " + vector3.getSize());

        System.out.println("Vector3 + Vector1 = " + vector3.getVectorsSum(vector1));
        System.out.println("(Static method) Vector1 + Vector2 = " + Vector.getVectorsSum(vector1, vector2));

        System.out.println("Vector3 - Vector1 = " + vector3.getVectorsDifference(vector1));
        System.out.println("(Static method) Vector1 - Vector2 = " + Vector.getVectorsDifference(vector1, vector2));

        System.out.println("Vector3 * Scalar(4) = " + vector3.getScalarMultiplication(4));
        System.out.println("(Static method) Vector1 * Vector2 = " + Vector.getScalarVectorsMultiplication(vector1, vector2));

        System.out.println("Revert Vector1 = " + vector1.getRevertVector());
    }
}
