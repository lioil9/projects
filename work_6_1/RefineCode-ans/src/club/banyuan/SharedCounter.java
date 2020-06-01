package club.banyuan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SharedCounter {

  static int counter = 0;


  public static void reset() {
    counter = 0;
  }

  public static int increment(int numThreads, int numIncrementsPerThread) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    List<Future<Integer>> list = new ArrayList<>();
    for (int i = 0; i < numThreads; i++) {
      Future<Integer> future = executorService.submit(() -> {
        synchronized (SharedCounter.class) {
          counter += numIncrementsPerThread;
          return counter;
        }
      });
      list.add(future);
    }
    list.forEach(s -> {
      try {
        s.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });
    return counter;
  }
}
