package ru.academits.java.nazimov.tree_main;

import ru.academits.java.nazimov.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> number = new Tree<>();

        number.add(8);
        number.add(3);
        number.add(10);
        number.add(1);
        number.add(6);
        number.add(14);
        number.add(4);
        number.add(7);
        number.add(13);

        System.out.println("Tree getNodeCount: " + number.getNodeCount());
        System.out.println();

        System.out.println("Tree contains(15): " + number.contains(15));
        System.out.println();

        System.out.println("Tree toPassInDepth: ");
        number.toPassInDepth();
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInDepth: Recursive");
        number.toPassInDepthRecursive();
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInWide: ");
        number.toPassInWide();
        System.out.println();
        System.out.println();

        System.out.println("Tree remove(8): " + number.remove(8));
        number.toPassInWide();
    }
}
