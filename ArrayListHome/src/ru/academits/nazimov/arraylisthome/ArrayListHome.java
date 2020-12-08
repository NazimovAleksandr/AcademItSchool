package ru.academits.nazimov.arraylisthome;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        // Задача 1
        ArrayList<String> lines = readLinesFromFile("ArrayListHome/input.txt");

        System.out.println("ArrayList, read from file: " + lines);

        // Задача 2
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 1, 7, 5, 6, 6, 7));

        removeEvenNumbers(numbers);
        System.out.println("ArrayList, remove even numbers: " + numbers);

        // Задача 3
        ArrayList<Integer> uniqueNumbers = getListWithoutDuplicates(numbers);

        System.out.println("ArrayList, unique numbers: " + uniqueNumbers);
    }

    public static ArrayList<String> readLinesFromFile(String fileName) {
        ArrayList<String> linesRead = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                linesRead.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return linesRead;
    }

    public static void removeEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(list.size());

        for (Integer e : list) {
            if (!listWithoutDuplicates.contains(e)) {
                listWithoutDuplicates.add(e);
            }
        }

        return listWithoutDuplicates;
    }
}
