package ru.academits.nazimov.arraylisthome;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        // Задача 1
        ArrayList<String> lines = new ArrayList<>();

        readFileIntoList(lines);
        System.out.println("ArrayList (read from file) " + lines);

        // Задача 2
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 1, 7, 5, 6, 6, 7));

        removeEvenNumbers(numbers);
        System.out.println("ArrayList (remove even numbers) " + numbers);

        // Задача 3
        ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);

        System.out.println("ArrayList (unique lines) " + uniqueNumbers);
    }

    public static void readFileIntoList(ArrayList<String> arrayList) {
        try (Scanner scanner = new Scanner(new FileReader("ArrayListHome/input.txt"))) {
            while (scanner.hasNext()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> arrayList) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        uniqueNumbers.ensureCapacity(arrayList.size());

        uniqueNumbers.add(arrayList.get(0));

        duplicate:
        for (Integer i : arrayList) {
            for (Integer j : uniqueNumbers) {
                if (i.equals(j)) {
                    continue duplicate;
                }
            }

            uniqueNumbers.add(i);
        }

        return uniqueNumbers;
    }
}
