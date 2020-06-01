package club.banyuan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SharedCounter {

  static int counter = 0;


  public static void reset() {
    counter = 0;
  }

  public static int increment(int numThreads, int numIncrementsPerThread)
      throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    List<Thread> rlt = new ArrayList<>();
    for (int i = 0; i < numThreads; i++) {
      Future<Integer> future = executorService.submit(() -> counter += numIncrementsPerThread);
//      Thread one = new Thread(() -> {
//        synchronized (SharedCounter.class) {
//          counter += numIncrementsPerThread;
//        }
//      });
//      one.start();
//      rlt.add(one);
    }
//    rlt.forEach(t -> {
//      try {
//        t.join();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });
    return counter;
  }
}
