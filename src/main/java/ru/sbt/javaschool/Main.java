package ru.sbt.javaschool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        List<Person> people = new ArrayList<>();
        people.add(new Person("Bob", 20, true));
        people.add(new Person("Sara", 20, false));
        people.add(new Person("Jessica", 30, false));
        people.add(new Person("John", 28, true));
        people.add(new Person("Max", 40, true));

        Map<String, Person> map = Streams.of(people)
                .filter(person -> person.getAge() > 25)
                .filter(Person::isMan)
                .transform(person -> new Person(person.getName(), person.getAge() + 2, person.isMan()))
                .toMap(Person::getName, person -> person);

        for (String s : map.keySet()) {
            System.out.println("key: " + s + " | " + map.get(s));
        }
    }
}
