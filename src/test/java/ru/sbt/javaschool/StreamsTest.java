package ru.sbt.javaschool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StreamsTest {
    private List<Person> people;

    @Before
    public void setUp() throws Exception {
        people = new ArrayList<>();
        people.add(new Person("Bob", 20, true));
        people.add(new Person("Sara", 20, false));
        people.add(new Person("Jessica", 30, false));
        people.add(new Person("John", 28, true));
        people.add(new Person("Max", 40, true));
    }

    @Test
    public void filter() {
        Streams<Person> personStream = Streams
                .of(this.people)
                .filter(person -> person.getAge() > 30);
        assertEquals(personStream.size(), 1);

        personStream = Streams
                .of(this.people)
                .filter(Person::isMan);
        assertEquals(personStream.size(), 3);
    }


    @Test
    public void toMapAndToTransform() {
        Map<String, Person> map = Streams
                .of(people)
                .filter(person -> person.getAge() > 25)
                .filter(Person::isMan)
                .transform(person -> new Person(person.getName(), person.getAge() + 2, person.isMan()))
                .toMap(Person::getName, person -> person);

        assertEquals(map.size(),2);
        assertTrue(map.containsKey("John"));
        assertTrue(map.containsKey("Max"));
        assertEquals(map.get("Max").getAge(), 42);
    }
}