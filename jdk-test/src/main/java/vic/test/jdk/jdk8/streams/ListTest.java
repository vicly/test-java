package vic.test.jdk.jdk8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vic Liu
 */
public class ListTest {

    public static void main(String[] args) {


        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("aaa-2");
        stringCollection.add("bbb-2");
        stringCollection.add("ccc-2");
        stringCollection.add("bbb-1");
        stringCollection.add("aaa-1");

        // filter
        stringCollection
                .stream()
                .filter(s -> s.startsWith("aaa"))
                .forEach(System.out::println);
        // aaa-2
        // aaa-1
        System.out.println("\n\n");




        // sorting
        stringCollection
                .stream()
                .filter(s -> s.startsWith("bbb"))
                .sorted()
                .forEach(System.out::println);
        // bbb-1
        // bbb-2
        System.out.println("\n\n");



        // map
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
        // AAA-2
        // BBB-2
        // ...



        // matching
        stringCollection.stream().allMatch(s -> s.startsWith("aaa")); // false
        stringCollection.stream().anyMatch(s -> s.startsWith("aaa")); // true



        // count
        stringCollection.stream().filter(s -> s.startsWith("a")).count(); // 2



        // reduce
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(reduced.get());


    }

}
