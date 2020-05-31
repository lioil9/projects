package club.banyuan;

import java.util.concurrent.locks.Condition;

public class DiningTable {

  public static void main(String[] args) {
    Fork fork1 = new Fork("筷子1");
    Fork fork2 = new Fork("筷子2");
    Fork fork3 = new Fork("筷子3");
    Fork fork4 = new Fork("筷子4");
    Fork fork5 = new Fork("筷子5");

    Philosopher philosopher1 = new Philosopher("哲学家1", fork1, fork5);
    Philosopher philosopher2 = new Philosopher("哲学家2", fork2, fork1);
    Philosopher philosopher3 = new Philosopher("哲学家3", fork3, fork2);
    Philosopher philosopher4 = new Philosopher("哲学家4", fork4, fork3);
    Philosopher philosopher5 = new Philosopher("哲学家5", fork5, fork4);
    philosopher1.start();
    philosopher2.start();
    philosopher3.start();
    philosopher4.start();
    philosopher5.start();

    try {
      philosopher1.join();
      philosopher2.join();
      philosopher3.join();
      philosopher4.join();
      philosopher5.join();
    } catch (InterruptedException ie){
      ie.printStackTrace();
    }
  }

}
