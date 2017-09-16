package vic.test.rxjava;

import com.google.common.collect.Lists;
import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Vic Liu
 */
public class Main {

    private static String heavyTask(String input) {
        try {
            System.out.format("%s: %s - handling %s",
                    System.currentTimeMillis(),
                    Thread.currentThread().getName(),
                    input).println();
            Thread.currentThread().sleep(1000);
            return input + "-processed";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws Exception {

        if (true) {
            List<String> source = Lists.newArrayList();
            for (int i = 'a'; i < 'z'; i++) {
                source.add(String.valueOf((char) i));
            }
            String[] arr = source.toArray(new String[source.size()]);

            Observable.from(arr)
                    .subscribeOn(Schedulers.computation())
                    .map((str) -> heavyTask(str))
                    .toBlocking()
                    .subscribe();

            Thread.sleep(20000);
            return;
        }



        if (true) {
            Observable.range(1, 21)
                    .groupBy((i) -> i % 3);
//                    .flatMap((g) -> {
//                        int key = g.getKey();
//                        g.forEach(i -> System.out.println(key + " - " + i));
//                    });

            return;
        }

        if (true) {

            // just
            Observable<Long> now = Observable.just(System.currentTimeMillis());
            now.subscribe(System.out::println);
            Thread.sleep(1000);
            now.subscribe(System.out::println);

            System.out.println("--------------------");
            // defer
            Observable<Long> deferNow = Observable.defer(() -> Observable.just(System.currentTimeMillis()));
            deferNow.subscribe(System.out::println);
            Thread.sleep(1000);
            deferNow.subscribe(System.out::println);

            return;
        }


        if (true) {
            Observable<String> data = Observable.just("one", "two", "three", "four", "five");
            Observable.zip(data, Observable.interval(1, TimeUnit.SECONDS), (d, t) -> {
                return d + " " + t;
            })
                    .toBlocking().forEach(System.out::println);


            return;
        }





        System.out.println(">>>>> Scan vs Reduce");
        Observable
                .range(0, 10)
                .reduce(0, (n1, n2) -> n1 + n2)
                .subscribe((x) -> System.out.println(x));

        System.out.println(" ... ... ");
        Observable
                .range(0, 10)
                .reduce(new ArrayList<>(), (list, i) -> {
                    System.out.println(">> " + list);
                    list.add(i);
                    return list;
                })
                .forEach(System.out::println);

        System.out.println("... vs ...");

        Observable
                .range(0, 10)
                .scan(new ArrayList<>(), (list, i) -> {
                    System.out.println(">> " + list);
                    list.add(i);
                    return list;
                })
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        Observable.create(subscriber -> {
            try {
                subscriber.onNext("data");
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(System.out::println);



    }

}
