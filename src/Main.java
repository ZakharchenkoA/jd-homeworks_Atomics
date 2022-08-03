import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    private static final long SIZE = 10;
    private static final int LOW_BOUND = 1000;
    private static final int HIGH_BOUND = 3000;
    private static final int QUANTITY = 3;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder stat = new LongAdder();


        for (int i = 1; i <= QUANTITY; i++) {
            new Random()
                    .ints(SIZE, LOW_BOUND, HIGH_BOUND)
                    .boxed().toList()
                    .forEach(j -> executorService.submit(() -> stat.add(j)));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("\nРезультат: " + stat.sum());
        executorService.shutdown();
    }
}
