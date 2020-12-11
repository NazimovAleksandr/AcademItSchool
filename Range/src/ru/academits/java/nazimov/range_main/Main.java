package ru.academits.java.nazimov.range_main;

import ru.academits.java.nazimov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1.4, 5);

        double number = 4.1;
        System.out.printf("Находится ли число %.2f в деапазоне чисел от %.2f до %.2f?%n%b%n", number,
                range1.getFrom(), range1.getTo(), range1.isInside(number));
        System.out.println();

        range1.print();
        System.out.println();

        range1.setFrom(3);
        range1.setTo(7);

        System.out.printf("Находится ли число %.2f в деапазоне чисел от %.2f до %.2f?%n%b%n", number,
                range1.getFrom(), range1.getTo(), range1.isInside(number));
        System.out.println();

        System.out.println("Length = " + range1.getLength());
        System.out.println();

        Range range2 = new Range(5, 7);
        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение двух интервалов ");

        System.out.print("Range1: ");
        range1.print();

        System.out.print(" + Range2: ");
        range2.print();

        System.out.print(" = ");
        Range.printArray(union);

        System.out.println();
        Range intersection = range2.getIntersection(range1);
        System.out.println("Пересечение двух интервалов");

        System.out.print("Range2: ");
        range2.print();

        System.out.print(" и Range1");
        range1.print();

        System.out.print(" = ");

        if (intersection != null) {
            intersection.print();
            System.out.println();
        } else {
            System.out.println("null");
        }

        System.out.println();
        Range[] difference = range1.getDifference(range2);
        System.out.println("Расность двух интервалов");

        System.out.print("Range1: ");
        range1.print();

        System.out.print(" - Range2: ");
        range2.print();

        System.out.print(" = ");
        Range.printArray(difference);

        System.out.println("Задача засчитана");
    }
}
