package ru.academits.java.nazimov.tree_main;

import ru.academits.java.nazimov.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> number = new Tree<>(Integer::compare);

        number.add(8);
        number.add(3);
        number.add(10);
        number.add(1);
        number.add(6);
        number.add(14);
        number.add(4);
        number.add(7);
        number.add(13);

        System.out.println("Tree getSize: " + number.getSize());
        System.out.println();

        System.out.println("Tree contains(13): " + number.contains(13));
        System.out.println();

        System.out.println("Tree toPassInDepth: ");
        number.passInDepth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInDepth: Recursive");
        number.passInDepthRecursive(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInWide: ");
        number.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        number.add(8);
        System.out.println("Tree toPassInWide: ");
        number.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Size " + number.getSize());
        System.out.println("Tree remove(3): " + number.remove(3));
        number.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println("Size " + number.getSize());
    }
}
