package ru.academits.java.nazimov.lambda_task.person_main;

import ru.academits.java.nazimov.lambda_task.person.Person;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> peoples = new ArrayList<>();

        peoples.add(new Person("Ivan", 22));
        peoples.add(new Person("Ira", 11));
        peoples.add(new Person("Misha", 12));
        peoples.add(new Person("Sasha", 30));
        peoples.add(new Person("Ira", 26));
        peoples.add(new Person("Tanya", 27));

        Stream<String> peopleNames1 = peoples.stream()
                .map(Person::getName)
                .distinct();
        peopleNames1.forEach(value -> System.out.print(value + " "));
        System.out.println();
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

        DoubleStream numberRoot = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt).limit(count);

        numberRoot.forEach(System.out::println);
    }
}
