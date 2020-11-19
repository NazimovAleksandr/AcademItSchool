package ru.academits.java.nazimov.shape;

import java.util.Comparator;

public class ComparatorArea implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o2.getArea(), o1.getArea());
    }
}
