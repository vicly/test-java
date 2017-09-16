package vic.test.rxjava;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * @author Vic Liu
 */
public class DefaultThreadBehavior {

    public static void main(String[] args) throws Exception {

        //
        // subscribeOn() impacts Observable computation
        //
        // observeOn()   impacts all Operator and Subscriber after its definition
        //
        // to multi subscribeOn(), first win
        //
        // to multi observeOn(), each one impacts Operator/Subscribe after its definition,
        //                       but before next observeOn()
        //

        System.out.println("Case 1: default behavior");
        System.out.println("--------------------------------------");
        Observable.fromCallable(thatReturnOneNumber())
                .map(i -> numberToString(i))
                .subscribe((str) -> printString(str));
        caseSeperator();


        System.out.println("Case 2: subscribeOn");
        System.out.println("--------------------------------------");
        Observable.fromCallable(thatReturnOneNumber())
                .subscribeOn(newSingleThread("1st_subscribeOnScheduler")) // first win
                .map(i -> numberToString(i))
                .subscribeOn(newSingleThread("2nd_subscribeOnScheduler"))
                .subscribe((str) -> printString(str));
        Thread.sleep(500);
        caseSeperator();


        System.out.println("Case 3: observeOn");
        System.out.println("--------------------------------------");
        Observable.fromCallable(thatReturnOneNumber())
                .observeOn(newSingleThread("1st_observeOnScheduler"))
                .map(i -> numberToString(i)) // io
                .observeOn(newSingleThread("2nd_observeOnScheduler"))
                .subscribe((str) -> printString(str)); // computation
        Thread.sleep(500);
        caseSeperator();


        System.out.println("Case 4: subscribeOn, then observeOn");
        System.out.println("--------------------------------------");
        Observable.fromCallable(thatReturnOneNumber())
                .subscribeOn(newSingleThread("subscribeOnScheduler"))
                .map(i -> numberToString(i))
                .observeOn(newSingleThread("observeOnScheduler"))
                .subscribe((str) -> printString(str)); // computation
        Thread.sleep(500);
        caseSeperator();


        System.out.println("Case 5 observeOn, then subscribeOn");
        System.out.println("--------------------------------------");
        Observable.fromCallable(thatReturnOneNumber())
                .observeOn(newSingleThread("observeOnScheduler"))
                .map(i -> numberToString(i))
                .subscribeOn(newSingleThread("subscribeOnScheduler"))
                .subscribe((str) -> printString(str)); // computation
        Thread.sleep(500);

    }

    private static Callable<Integer> thatReturnOneNumber() {
        return () -> {
            log("Observable");
            return 1;
        };
    }

    private static String numberToString(int number) {
        log("Operator");
        return String.valueOf(number);
    }

    private static void printString(String string) {
        log("Subscriber");
    }

    private static Scheduler newSingleThread(final String name) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat(name).build();
        Executor executor = Executors.newSingleThreadExecutor(factory);
        return Schedulers.from(executor);
    }

    private static void log(String name) {
        System.out.format("%-12s: %s", name, Thread.currentThread().getName()).println();
    }

    private static void caseSeperator() {
        IntStream.range(1, 3).forEach(i -> System.out.println());
    }

}


