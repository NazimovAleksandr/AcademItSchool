package ru.academits.java.nazimov.lambda_task.person_main;

import ru.academits.java.nazimov.lambda_task.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> peoples = new ArrayList<>(Arrays.asList(
                (new Person("Ivan", 22)),
                (new Person("Ira", 11)),
                (new Person("Misha", 12)),
                (new Person("Sasha", 30)),
                (new Person("Ira", 26)),
                (new Person("Tanya", 27))
        ));

        List<String> namesList = peoples.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(namesList);
        System.out.println();

        String peopleNames2 = peoples.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(peopleNames2);
        System.out.println();

        Stream<Person> peopleListMinors = peoples.stream()
                .filter(person -> person.getAge() < 18)
                .peek(System.out::println);

        double averageAge = peopleListMinors.collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Средний возраст " + averageAge);
        System.out.println();

        Map<String, Double> averagePeopleAge = peoples.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(averagePeopleAge);
        System.out.println();

        Stream<Person> adultsPeople = peoples.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() < 45)
                .sorted((person1, person2) -> person2.getAge() - person1.getAge());
        adultsPeople.forEach(System.out::println);
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько корней чисел вывести?");
        System.out.print("Введите число: ");
        int count = scanner.nextInt();

        DoubleStream numberRoot = DoubleStream.iterate(0, x -> x < count, x -> x + 1).map(Math::sqrt);

        numberRoot.forEach(System.out::println);
    }
}
