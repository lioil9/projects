package club.banyuan;

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

  public void takeFork(){
    lock.lock();
//    while (this.status){
//      try {
//        this.wait();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
      status = true;
//    }
    lock.unlock();
  }
  public void putFork(){
    lock.lock();
    status = false;
    forkLock.signalAll();
    lock.unlock();
  }
}
