public class AbstractPhilosopher extends Thread {

  private Chopsticks left;
  private Chopsticks right;
  private int eatTimes;
  private int thinkTimes;

  public AbstractPhilosopher(){
    this.eatTimes = 0;
    this.thinkTimes = 0;
  }
  public void eat() {
    if (!left.take(500)) {
      System.out.println("没拿到筷子，不吃了");
      return;
    }
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (!right.take(500)) {
      System.out.println("没拿到筷子，不吃了");
      left.put();
      return;
    }
    System.out.println(getName() + ",吃饭");
    eatTimes++;
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    left.put();
    right.put();
    System.out.println(getName() + ",吃饭结束");
  }

  public void thinking() {
    System.out.println(getName() + ",思考");
    thinkTimes++;
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(getName() + ",思考结束");
  }

  public Chopsticks getLeft() {
    return left;
  }

  public void setLeft(Chopsticks left) {
    this.left = left;
  }

  public Chopsticks getRight() {
    return right;
  }

  public void setRight(Chopsticks right) {
    this.right = right;
  }

  public int getEatTimes() {
    return eatTimes;
  }

  public int getThinkTimes() {
    return thinkTimes;
  }
}
