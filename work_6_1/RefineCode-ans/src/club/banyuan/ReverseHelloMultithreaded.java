package club.banyuan;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReverseHelloMultithreaded {

  public static void doReverseHello() {
    helloThread(1);
  }

  public static void helloThread(int num) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    if (num <= 50) {
      Future<String> future = executorService.submit(() ->
        helloThread(num+1),num+"");
      try {
        System.out.println("Hello from thread " + future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    executorService.shutdown();
  }
}
