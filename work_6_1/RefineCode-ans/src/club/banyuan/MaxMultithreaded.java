package club.banyuan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MaxCallable implements Callable<Double> {

  private int lo, hi;
  private int[] arr;
  private double ans = 0;

  public MaxCallable(int[] arr, int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
    this.arr = arr;
  }

  @Override
  public Double call() {
    ans = Math.sin(arr[lo]);
    for (int i = lo; i < hi; i++) {
      double sin = Math.sin(arr[i]);
      if (ans < sin) {
        ans = sin;
      }
    }
    return ans;
  }

}



public class MaxMultithreaded {

  /**
   * 计算数组元素的sin值之后，返回最大值。
   *
   * @param arr 目标数组
   * @return sin(数组元素)的最大值
   * @throws InterruptedException 不应该出现此异常
   */
  public static double max(int[] arr, int numThreads) throws InterruptedException {
    int len = arr.length;
    double ans = -Double.MAX_VALUE;

    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    List<Future<Double>> futureList = new ArrayList<>(numThreads);
    // 创建并启动线程。
    for (int i = 0; i < numThreads; i++) {
      MaxCallable maxCallable = new MaxCallable(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
      futureList.add(executorService.submit(maxCallable));
    }


    // 等待线程完成并计算它们的结果。
    for (int i = 0; i < numThreads; i++) {
      try {
        Future<Double> future = futureList.get(i);
        if (ans < future.get()) {
          ans = future.get();
        }
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    return ans;
  }
}
