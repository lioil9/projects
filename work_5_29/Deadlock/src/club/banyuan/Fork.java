package club.banyuan;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
  private String name;
  private boolean status;
  private Lock lock = new ReentrantLock();
  private Condition forkLock = lock.newCondition();

  public Fork(String name) {
    this.name = name;
    this.status = false;
  }

  public String getName() {
    return name;
  }

  public boolean isStatus() {
    return status;
  }

  public boolean takeFork(){
    try {
      if(!lock.tryLock(500, TimeUnit.MILLISECONDS)){
        return false;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return true;
  }


  public void putFork(){
    lock.unlock();
  }

}
