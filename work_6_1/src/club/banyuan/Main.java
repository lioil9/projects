package club.banyuan;

public class Main {

  public static void main(String[] args) {
    Test test = new Test();

    test.start();
    System.out.println("外部："+test.isInterrupted());
    test.interrupt();
    System.out.println("外部："+test.isInterrupted());
    try {
      test.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
