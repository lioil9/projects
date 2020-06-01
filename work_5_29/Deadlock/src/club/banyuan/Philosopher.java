package club.banyuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Philosopher extends Thread {

  private String name;
  private Fork leftFork;
  private Fork rightFork;
  private int thinkTimes;
  private int eatTimes;
  private static int count = 0;
  ExecutorService executorService = Executors.newCachedThreadPool();



  public Philosopher(String name, Fork leftFork, Fork rightFork) {
    this.name = name;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
    this.thinkTimes = 0;
    this.eatTimes = 0;
  }


  public void eat() {
    if (!this.leftFork.takeFork()) {
      System.out.println(name + "没拿到" + leftFork.getName());
      this.rightFork.putFork();
      System.out.println(name + "放下" + rightFork.getName());
      return;
    } else {
      System.out.println(name + "拿起" + leftFork.getName());
    }
    if (!this.rightFork.takeFork()) {
      System.out.println(name + "没拿到" + rightFork.getName());
      this.leftFork.putFork();
      System.out.println(name + "放下" + leftFork.getName());
      return;
    } else {
      System.out.println(name + "拿起" + rightFork.getName());
    }
    eatTimes++;
    System.out.println("  " + this.name + "开始吃饭了");
    try {
      sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.leftFork.putFork();
    this.rightFork.putFork();
  }

  public void think() {
    thinkTimes++;
    System.out.println("  " + this.name + "开始思考了");
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  public void run() {
    while (count++ < 50) {
      eat();
      think();
    }
    System.out.println(name + "吃了" + eatTimes + "次，思考了" + thinkTimes + "次。");
  }

}
