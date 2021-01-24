package ru.academits.java.nazimov.array_list_main;

import ru.academits.java.nazimov.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.trimToSize();
        System.out.println("ArrayList.size = " + arrayList.size());
        System.out.println("ArrayList.isEmpty = " + arrayList.isEmpty());
        System.out.println("ArrayList.contains (5) = " + arrayList.contains(5));

        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        System.out.println();
        System.out.println("ArrayList.size = " + arrayList.size());
        System.out.println("ArrayList = " + arrayList);

        System.out.println();
        System.out.println("ArrayList.remove (1) = " + arrayList.remove(Integer.valueOf(1)));
        System.out.println("ArrayList = " + arrayList);

        ArrayList<Integer> collection = new ArrayList<>();

        collection.add(1);
        collection.add(2);
        collection.add(3);

        System.out.println("Collection = " + collection);
        System.out.println();

        arrayList.trimToSize();
        System.out.println("ArrayList.addAll Collection = " + arrayList.addAll(collection));
        System.out.println("ArrayList = " + arrayList);

        System.out.println();
        System.out.println("ArrayList.addAll index(0) Collection = " + arrayList.addAll(0, collection));
        System.out.println("ArrayList = " + arrayList);

        arrayList.clear();
        System.out.println();
        System.out.println("ArrayList.clear");
        System.out.println("ArrayList = " + arrayList);

        arrayList.add(44);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        System.out.println();
        System.out.println("ArrayList = " + arrayList);
        System.out.println("ArrayList.set index(0) element(0) = " + arrayList.set(0, 0));
        System.out.println("ArrayList = " + arrayList);

        System.out.println();
        arrayList.trimToSize();
        System.out.println("ArrayList.remove index(4) = " + arrayList.remove(4));
        System.out.println("ArrayList = " + arrayList);

        System.out.println();
        System.out.println("ArrayList = " + arrayList);
        System.out.println("Collection = " + collection);
        System.out.println("ArrayList.retainAll Collection = " + arrayList.retainAll(collection));
        System.out.println("ArrayList = " + arrayList);

        System.out.println();
        System.out.println("ArrayList = " + arrayList);
        System.out.println("Collection = " + collection);
        System.out.println("ArrayList.containsAll Collection = " + arrayList.containsAll(collection));

        System.out.println();
        System.out.println("ArrayList = " + arrayList);
        System.out.println("Collection = " + collection);
        System.out.println("ArrayList.removeAll Collection = " + arrayList.removeAll(collection));
        System.out.println("ArrayList = " + arrayList);

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        System.out.println();
        System.out.println("ArrayList = " + arrayList);
        System.out.println("Collection = " + collection);
        System.out.println("ArrayList.containsAll Collection = " + arrayList.containsAll(collection));

        ArrayList<Integer> arrayList2 = new ArrayList<>(15);

        for (int i = 0; i < 15; i++) {
            arrayList2.add(i);
        }

        arrayList.ensureCapacity(arrayList.size() + arrayList2.size());

        arrayList.addAll(arrayList2);
        System.out.println();
    }
}
