package club.banyuan;

import java.util.Arrays;

class MaxThread extends Thread {
  private int lo, hi;
  private int[] arr;
  private double max;

  public MaxThread(int[] arr, int lo, int hi){
    this.arr = arr;
    this.lo = lo;
    this.hi = hi;
  }

  @Override
  public void run() {
    max = Double.NEGATIVE_INFINITY;
    for (int i = lo; i < hi; i++) {
      double sinValue = Math.sin(arr[i]);
      if(sinValue > max){
        max = sinValue;
      }
    }
  }

  public double getMax() {
    return max;
  }
}

public class MaxMultithreaded {

  /**
   * 求数组元素的最大值。
   *
   * @param arr 目标数组
   * @return 数组元素的最大值
   * @throws InterruptedException 不应该出现此异常
   */
  public static double max(int[] arr, int numThreads) throws InterruptedException {
    int len = arr.length;
    double[] maxArr = new double[numThreads];

    MaxThread[] ts = new MaxThread[numThreads];
    for (int i = 0; i < numThreads; i++) {
      ts[i] = new MaxThread(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
      ts[i].start();
    }

    for (int i = 0; i < numThreads; i++) {
      ts[i].join();
      maxArr[i] = ts[i].getMax();
    }

    Arrays.sort(maxArr);

    return maxArr[numThreads-1];
  }
}
