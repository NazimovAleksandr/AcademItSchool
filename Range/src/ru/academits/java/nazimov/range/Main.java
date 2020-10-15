package ru.academits.java.nazimov.range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1.4, 5);

        double number = 4.1;
        System.out.printf("Находится ли число %.2f в деапазоне чисел от %.2f до %.2f?%n%b%n", number,
                range1.getFrom(), range1.getTo(), range1.isInside(number));
        System.out.println();

        System.out.println("From = " + range1.getFrom());
        System.out.println("To = " + range1.getTo());
        System.out.println();

        range1.setFrom(1.7);
        range1.setTo(5.2);

        System.out.printf("Находится ли число %.2f в деапазоне чисел от %.2f до %.2f?%n%b%n", number,
                range1.getFrom(), range1.getTo(), range1.isInside(number));
        System.out.println();


        System.out.println("Length = " + range1.getLength());
        System.out.println();

        Range range2 = new Range(6, 9);
        Range[] range3 = range1.getIntervalsConcatenation(range2);

        for (Range e : range3) {
            e.print();
        }

        System.out.println();
        Range range4 = range2.getIntervalsIntersection(range1);

        if (range4 != null) {
            range4.print();
        } else {
            System.out.println("Range4 = null");
        }

        System.out.println();
        Range[] range5 = range1.getIntervalDifference(range2);

        for (Range e : range5) {
            e.print();
        }

        System.out.println();
    }
}
