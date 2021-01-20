package ru.academits.java.nazimov.hash_table_main;

import ru.academits.java.nazimov.hash_table.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> words1 = new HashTable<>();

        String s = null;

        words1.add("String2");
        words1.add("String3");
        words1.add("String3");
        words1.add("String3");
        words1.add(s);

        System.out.println("Words1");
        System.out.println("Size: " + words1.size());
        System.out.print(words1);
        System.out.println();

        HashTable<String> words2 = new HashTable<>(10);

        words2.addAll(words1);

        System.out.println("Words2");
        System.out.println(words2);
    }
}
