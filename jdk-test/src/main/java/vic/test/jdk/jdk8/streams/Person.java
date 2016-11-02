package vic.test.jdk.jdk8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Vic Liu
 */
public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }


    public static void main(String[] args) {

        // http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12)
        );

        // group by
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));


        // averagingInt
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);


        // Collector
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher
        String names = persons.stream().collect(personNameCollector);
        System.out.println(names); // MAX | PETER | PAMELA | DAVID
    }
}
