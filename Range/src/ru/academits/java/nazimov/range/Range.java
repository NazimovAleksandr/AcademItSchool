package ru.academits.java.nazimov.range;

public class Range {
    double from;
    double to;

    public Range(double from, double to) {
        if (from < to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public Range() {
    }

    public double getFrom() {
        return this.from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return this.to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public void print() {
        System.out.println("From = " + this.getFrom());
        System.out.println("To = " + this.getTo());
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntervalsIntersection(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (from < rangeFrom && to > rangeTo || from > rangeFrom && to < rangeTo) {
            double maxFrom = Math.max(from, rangeFrom);
            double minTo = Math.min(to, rangeTo);
            return new Range(maxFrom, minTo);
        }

        if (to > rangeTo && from < rangeTo) {
            return new Range(from, rangeTo);
        }

        if (to < rangeTo && to > rangeFrom) {
            return new Range(rangeFrom, to);
        }

        return null;
    }

    public Range[] getIntervalsConcatenation(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (from <= rangeTo && to >= rangeFrom) {
            double minFrom = Math.min(from, rangeFrom);
            double maxTo = Math.max(to, rangeTo);
            return new Range[]{new Range(minFrom, maxTo)};
        }

        return new Range[]{new Range(from, to), new Range(rangeFrom, rangeTo)};
    }

    public Range[] getIntervalDifference(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (from >= rangeFrom && to <= rangeTo) {
            return new Range[]{new Range()};
        }

        if (to > rangeFrom && from < rangeFrom && to < rangeTo) {
            return new Range[]{new Range(from, rangeFrom)};
        }

        if (to > rangeTo && from < rangeTo && from > rangeFrom) {
            return new Range[]{new Range(rangeTo, to)};
        }

        if (from < rangeFrom && to > rangeTo) {
            return new Range[]{new Range(from, rangeFrom), new Range(rangeTo, to)};
        }

        return new Range[]{new Range(from, to), new Range(rangeFrom, rangeTo)};
    }
}
