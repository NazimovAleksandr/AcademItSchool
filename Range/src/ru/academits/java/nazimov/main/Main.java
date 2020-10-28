package ru.academits.java.nazimov.main;

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

        range1.setFrom(1.7);
        range1.setTo(5.2);

        System.out.printf("Находится ли число %.2f в деапазоне чисел от %.2f до %.2f?%n%b%n", number,
                range1.getFrom(), range1.getTo(), range1.isInside(number));
        System.out.println();


        System.out.println("Length = " + range1.getLength());
        System.out.println();

        Range range2 = new Range(5.2, 9);
        Range[] union = range1.getUnion(range2);
        Range.printArray(union);

        System.out.println();
        Range intersection = range2.getIntersection(range1);

        if (intersection != null) {
            intersection.print();
        } else {
            System.out.println("Range4 = null");
        }

        System.out.println();
        Range[] difference = range2.getDifference(range1);
        Range.printArray(difference);
    }
}
