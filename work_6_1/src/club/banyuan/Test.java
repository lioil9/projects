package club.banyuan;

public class Test extends Thread {


  @Override
  public void run() {
    while (true){
      try {
        System.out.println("1");
        sleep(1000);
      } catch (InterruptedException e) {
        System.out.println(this.isInterrupted());
//        e.printStackTrace();
        return;
      }

    }
  }
}
