package ru.academits.java.nazimov.lambda_task.person_main;

import ru.academits.java.nazimov.lambda_task.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 22),
                new Person("Ira", 11),
                new Person("Misha", 12),
                new Person("Sasha", 45),
                new Person("Ira", 20),
                new Person("Tanya", 27)
        );

        List<String> namesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(namesList);
        System.out.println();

        String peopleNames = namesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(peopleNames);
        System.out.println();

        Stream<Person> minorsList = persons.stream()
                .filter(person -> person.getAge() < 18)
                .peek(System.out::println);

        double averageAge = minorsList.collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Средний возраст " + averageAge);
        System.out.println();

        Map<String, Double> averageAgeByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(averageAgeByNames);
        System.out.println();

        Stream<Person> adultsPeople = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge());
        adultsPeople.forEach(System.out::println);
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько корней чисел вывести?");
        System.out.print("Введите число: ");
        int count = scanner.nextInt();

        DoubleStream numbersRoots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt).limit(count);

        numbersRoots.forEach(System.out::println);
    }
}
