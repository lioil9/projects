package club.banyuan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
  private String name;
  private Fork rightFork;
  private Fork leftFork;

  public Philosopher(String name, Fork rightFork, Fork leftFork){
    this.name = name;
    this.rightFork = rightFork;
    this.leftFork = leftFork;
  }


  public void eat(){
      System.out.println(this.name+"开始吃饭了");
  }

  public void think(){
      System.out.println(this.name+"开始思考了");
  }

  public void run() {
    while (true) {
      if (!this.leftFork.isStatus() && !this.rightFork.isStatus()) {
        this.leftFork.takeFork();
        System.out.println(this.name + "拿起左手" + this.leftFork.getName());
        if (!this.rightFork.isStatus()) {
          this.rightFork.takeFork();
          System.out.println(this.name + "拿起右手" + this.rightFork.getName());
          this.eat();
          try {
            sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          this.leftFork.putFork();
          System.out.println(this.name + "放下左手" + this.leftFork.getName());
          this.rightFork.putFork();
          System.out.println(this.name + "放下右手手" + this.rightFork.getName());
        } else {
          this.leftFork.putFork();
          System.out.println(this.name + "放下左手" + this.leftFork.getName() + "右手" + this.rightFork.getName() + "没法拿");
          this.think();
          try {
            sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

}
