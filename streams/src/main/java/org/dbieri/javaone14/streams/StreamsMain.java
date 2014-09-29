package org.dbieri.javaone14.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbieri on 28.09.14.
 */
public class StreamsMain {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Dan", "28.10.1974"));
        persons.add(new Person("Michu", "05.04.1977"));

        persons.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .forEach(p -> System.out.println(p.getName()));


    }
}
