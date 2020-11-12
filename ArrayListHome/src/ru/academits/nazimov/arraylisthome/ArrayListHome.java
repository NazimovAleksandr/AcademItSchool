package ru.academits.nazimov.arraylisthome;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        // Задача 1
        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader("ArrayListHome/input.txt"))) {
            while (scanner.hasNext()) {
                numbers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ArrayList (read from file) " + numbers);

        // Задача 2
        numbers.removeIf(integer -> (integer % 2 == 0));
        System.out.println("ArrayList (remove even numbers) " + numbers);

        // Задача 3 - если для это кода есть готовое решение, подскажите какое (я искал, но найти не получилось)
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            int lastIndexOf = numbers.lastIndexOf(numbers.get(i));

            if (i == lastIndexOf) {
                uniqueNumbers.add(numbers.get(i));
            }
        }

        System.out.println("ArrayList (unique numbers) " + uniqueNumbers);
    }
}
