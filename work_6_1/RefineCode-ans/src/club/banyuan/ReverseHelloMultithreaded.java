package club.banyuan;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReverseHelloMultithreaded {

  public static void doReverseHello() {
    helloThread(1);
  }

  public static void helloThread(int num) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    if (num <= 50) {

      Thread thread = new Thread(() -> helloThread(num + 1), num + "");
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Hello from thread " + thread.getName());
    }
  }
}
