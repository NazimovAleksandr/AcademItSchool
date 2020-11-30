package ru.academits.nazimov.arraylisthome;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        // Задача 1
        try {
            FileReader fileReader = new FileReader("ArrayListHome/input.txt");

            ArrayList<String> lines = readLinesFromFile(fileReader);

            System.out.println("ArrayList, read from file: " + lines);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }


        // Задача 2
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 1, 7, 5, 6, 6, 7));

        removeEvenNumbers(numbers);
        System.out.println("ArrayList, remove even numbers: " + numbers);

        // Задача 3
        ArrayList<Integer> uniqueNumbers = getListWithoutDuplicates(numbers);

        System.out.println("ArrayList, unique numbers: " + uniqueNumbers);
    }

    public static ArrayList<String> readLinesFromFile(FileReader fileReader) {
        Scanner scanner = new Scanner(fileReader);
        ArrayList<String> arrayList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            arrayList.add(scanner.nextLine());
        }

        return arrayList;
    }

    public static void removeEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> arrayList) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>(arrayList.size());

        for (Integer e : arrayList) {
            if (!uniqueNumbers.contains(e)) {
                uniqueNumbers.add(e);
            }
        }

        return uniqueNumbers;
    }
}
