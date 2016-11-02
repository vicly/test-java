package vic.test.metrics;

import com.codahale.metrics.*;
import com.codahale.metrics.Timer;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Hello World
 *
 * @author Vic Liu
 */
public class GetStarted {

    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) {
        startReport();

        List<Integer> list = new LinkedList<>();

        Meter requests = metrics.meter(name(GetStarted.class, "requests")); // e.g. request per second

        // list.size() might slow down
        Gauge<Integer> listSizeGauge = () -> list.size();
        Counter listSizeCounter = metrics.counter(name(GetStarted.class, "list", "count"));

        Histogram httpResponseSize = metrics.histogram(name(GetStarted.class, "response-sizes"));

        Timer responses = metrics.timer(name(GetStarted.class, "responses"));


        metrics.register(name(GetStarted.class, "list", "size"), listSizeGauge);


        Random random = new Random();
        final long ONE_MINUTE = 60 * 1000;
        long start = System.currentTimeMillis();
        while ( (System.currentTimeMillis() - start) < ONE_MINUTE) {
            requests.mark();

            if (random.nextInt() % 5 == 0) {
                list.add(2);
                listSizeCounter.inc();
            }

            int responseSize = random.nextInt(1000000);
            httpResponseSize.update(responseSize);


            final Timer.Context ctx = responses.time();
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
            } finally {
                ctx.stop();
            }
        }
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(5, TimeUnit.SECONDS);
    }

}
