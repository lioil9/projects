import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {

  private int code;
  private Lock lock = new ReentrantLock();

  public Chopsticks(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void take() {
    System.out.println(Thread.currentThread().getName() + ",准备拿起筷子:" + code);
    lock.lock();
    System.out.println(Thread.currentThread().getName() + "拿起筷子:" + code);
  }

  public boolean take(long maxWaitTime) throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + ",准备拿起筷子:" + code);
      if (!lock.tryLock(maxWaitTime, TimeUnit.MILLISECONDS)) {
        return false;
      }
    System.out.println(Thread.currentThread().getName() + "拿起筷子:" + code);
    return true;
  }

  public void put() {
    System.out.println(Thread.currentThread().getName() + "放下筷子：" + code);
    lock.unlock();
  }
}
