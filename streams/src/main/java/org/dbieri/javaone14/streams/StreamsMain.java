package org.dbieri.javaone14.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbieri on 28.09.14.
 */
public class StreamsMain {

    public static boolean isPrime(int number) {
        return number > 1 && IntStream.range(2, number).noneMatch(i -> number % i == 0);

    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Dan", "28.10.1974"));
        persons.add(new Person("Michu", "05.04.1977"));

        persons.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .forEach(p -> System.out.println(p.getName()));

        System.out.println(Stream.iterate(100000000, e -> e + 1).filter(e -> isPrime(e)).findFirst().get());


    }
}
