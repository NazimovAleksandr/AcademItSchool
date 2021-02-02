package ru.academits.java.nazimov.tree_main;

import ru.academits.java.nazimov.tree.Tree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbersTree1 = new Tree<>(Comparator.nullsLast(Integer::compareTo));
        numbersTree1.remove(null);

        numbersTree1.add(10);
        numbersTree1.add(14);
        numbersTree1.add(12);
        numbersTree1.add(null);

        numbersTree1.passInWidth(integer -> System.out.print("[" + integer + "] "));

        System.out.println(numbersTree1.remove(14));
        numbersTree1.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println(numbersTree1.getSize());

        Tree<Integer> numbersTree2 = new Tree<>();

        numbersTree2.add(8);
        numbersTree2.add(3);
        numbersTree2.add(10);
        numbersTree2.add(1);
        numbersTree2.add(6);
        numbersTree2.add(14);
        numbersTree2.add(4);
        numbersTree2.add(7);
        numbersTree2.add(13);

        System.out.println("Tree getSize: " + numbersTree2.getSize());
        System.out.println();

        System.out.println("Tree contains(13): " + numbersTree2.contains(13));
        System.out.println();

        System.out.println("Tree toPassInDepth: ");
        numbersTree2.passInDepth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInDepth: Recursive");
        numbersTree2.passInDepthRecursive(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Tree toPassInWide: ");
        numbersTree2.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        numbersTree2.add(8);
        System.out.println("Tree toPassInWide: ");
        numbersTree2.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println();

        System.out.println("Size " + numbersTree2.getSize());
        System.out.println("Tree remove(3): " + numbersTree2.remove(3));
        numbersTree2.passInWidth(integer -> System.out.print("[" + integer + "] "));
        System.out.println();
        System.out.println("Size " + numbersTree2.getSize());
    }
}
