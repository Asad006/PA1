import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.atomic.AtomicLong;

class Counter implements Runnable {
    private static final AtomicLong counter = new AtomicLong(0);
    private static final AtomicLong nPrime = new AtomicLong(0);
    private static final AtomicLong sum = new AtomicLong(0);
    private static final long limit = (long) Math.pow(10, 8);
    private static final int threadPoolSize = 8;
    static long[] primesArray = new long[(int) limit];


    public static void main(String[] args) throws IOException {
        long current_local_time = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        Runnable worker = new Counter();
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        long elapsedTime = System.currentTimeMillis() - current_local_time;


        Arrays.sort(primesArray);

        //writing results to the file
        FileWriter myWriter = new FileWriter("primesPA1.txt");

        System.out.println(elapsedTime);
        myWriter.write("Execution time : " + elapsedTime + "ms.\n");
        myWriter.write("Number of Primes := " + nPrime + ".\n");
        myWriter.write("Sum of all primes found : " + sum.get() + "\n");
        myWriter.write("Last 10 primes found :\n");

        for (int i = primesArray.length - 10; i < primesArray.length; i++) {
            myWriter.write(primesArray[i] + "\n");
        }

        myWriter.write("\n");

        myWriter.close();
    }

    @Override
    public void run() {
        long i = counter.getAndIncrement();

        while ((i < limit) ) {
            //System.out.println(i);
            if (isPrime(i)) {

                sum.addAndGet(i);
                nPrime.incrementAndGet();
                primesArray[(int) nPrime.get()] = i;

            }

            i = counter.getAndIncrement();

        }
    }

    static boolean isPrime(long n) {
        if (n < 2)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return false;
        }

        return true;
    }

}