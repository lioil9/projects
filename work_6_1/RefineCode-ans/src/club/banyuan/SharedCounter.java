package club.banyuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedCounter {

  static int counter = 0;


  public static void reset() {
    counter = 0;
  }

  public static int increment(int numThreads, int numIncrementsPerThread)
      throws InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
//    List<Future<?>> list = new ArrayList<>();

    for (int i = 0; i < numThreads; i++) {
      executorService.submit(() -> {
        synchronized (SharedCounter.class) {
          counter += numIncrementsPerThread;
        }
      });
//      list.add(future);
    }
//    list.forEach(s -> {
//      try {
//        s.get();
//      } catch (InterruptedException | ExecutionException e) {
//        e.printStackTrace();
//      }
//    });
    executorService.shutdown();
    executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
//    executorService.shutdownNow();
    return counter;
  }
}
