package ru.academits.java.nazimov.list_main;

import ru.academits.java.nazimov.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers1 = new List<>();

        numbers1.add(11);
        numbers1.add(22);
        numbers1.add(33);
        numbers1.addToBeginning(0);
        numbers1.add(55, 4);
        numbers1.add(44);
        numbers1.add(55);
        numbers1.add(66);

        System.out.println("List.getCount");
        System.out.println(numbers1.getCount());
        System.out.println();

        System.out.println("List.getHeadData");
        System.out.println(numbers1.getHeadData());
        System.out.println();

        System.out.println("List.get(index 2)");
        System.out.println(numbers1.get(2));
        System.out.println();

        System.out.println("List.set(Integer 44, index 4)");
        System.out.println(numbers1.set(44, 4));
        System.out.println();

        System.out.println("List.remove(index 4)");
        System.out.println(numbers1.remove(4));
        System.out.println();

        System.out.println("List.remove(element 6)");
        System.out.println(numbers1.remove(Integer.valueOf(6)));
        System.out.println();

        System.out.println("List.removeFirstItem");
        System.out.println(numbers1.removeFirstItem());
        System.out.println();

        System.out.println("List.get(index 0)");
        System.out.println(numbers1.get(0));
        System.out.println();

        System.out.println("Numbers1");
        System.out.println(numbers1);
        System.out.println();

        System.out.println("List.revert");
        numbers1.revert();

        System.out.println("Numbers1");
        System.out.println(numbers1);
        System.out.println();

        List<Integer> numbers2 = numbers1.copy();
        numbers1.removeFirstItem();
        System.out.println("Numbers2");
        System.out.println(numbers2);
    }
}
