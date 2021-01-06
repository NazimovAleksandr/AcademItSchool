package ru.academits.java.nazimov.list_main;

import ru.academits.java.nazimov.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers1 = new List<>();

        numbers1.add(33);
        numbers1.add(11);
        numbers1.addFirst(0);
        numbers1.add(2, 55);
        numbers1.add(6);
        numbers1.add(6);

        System.out.println("List.getCount");
        System.out.println(numbers1.getCount());
        System.out.println();

        System.out.println("List.getFirst");
        System.out.println(numbers1.getFirst());
        System.out.println();

        System.out.println("List.get(index 2)");
        System.out.println(numbers1.get(2));
        System.out.println();

        System.out.println("List.set(index 2, Integer 44)");
        System.out.println(numbers1.set(2, 44));
        System.out.println();

        System.out.println("List.remove(index 3)");
        System.out.println(numbers1.remove(3));
        System.out.println();

        System.out.println("List.remove(element 6)");
        System.out.println(numbers1.remove(Integer.valueOf(6)));
        System.out.println();

        System.out.println("List.removeFirst");
        System.out.println(numbers1.removeFirst());
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

        List<Integer> numbers2 = new List<>();
        List<Integer> numbers3 = numbers2.copy();
        System.out.println("Numbers3");
        System.out.println(numbers3);
    }
}
