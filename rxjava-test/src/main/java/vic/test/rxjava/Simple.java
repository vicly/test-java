package vic.test.rxjava;

import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Vic Liu
 */
public class Simple {

    public static void main(String[] args) {

        Observable<String> observable = Observable.just("Hello", "World");
        // consume
        observable.subscribe(System.out::println);
        // consume again
        observable.first().subscribe(System.out::println);
        observable.subscribe(System.out::println);

        System.out.println();
        observable
                .zipWith(
                        Observable.range(1, Integer.MAX_VALUE),
                        (word, num) -> String.format("%2d.%s", num, word))
                .subscribe(System.out::println);

        System.out.println();
        observable
                .flatMap(word -> Observable.from(word.split("")))
                .zipWith(
                        Observable.range(1, Integer.MAX_VALUE),
                        (word, num) -> String.format("%2d.%s", num, word))
                .subscribe(System.out::println);

        System.out.println(">>>>> Error Handling");

        Observable.just("good1", "bad1", "good2", "bad2")
                .map(word -> {
                    if (word.startsWith("bad")) {
                        throw new RuntimeException("fake error caused by " + word);
                    } else {
                        return word + "-updated";
                    }})
                .retryWhen(attempts -> {
                    return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
                        System.out.println("delay retry by " + i + " second(s)");
                        return Observable.timer(i, TimeUnit.SECONDS);
                    }).concatWith(Observable.error(new RuntimeException("Failed after 3 retries")));
                })
                .toBlocking()
                .subscribe((word) -> System.out.println("onNext: " + word),
                        (t) -> t.printStackTrace(),
                        () -> System.out.println("onComplete!"));

        System.out.println(">>>>> Scan vs Reduce");
        Observable
                .range(0, 10)
                .reduce(0, (n1, n2) -> n1 + n2)
                .subscribe((x) -> System.out.println(x));

        System.out.println(" ... ... ");
        Observable
                .range(0, 10)
                .reduce(new ArrayList<>(), (list, i) -> {
                    list.add(i);
                    return list;
                })
                .forEach(System.out::println);

        System.out.println("... vs ...");

        Observable
                .range(0, 10)
                .scan(new ArrayList<>(), (list, i) -> {
                    list.add(i);
                    return list;
                }).
                forEach(System.out::println);

    }
}
