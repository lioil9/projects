public class Time {
  public static void gameClearance() {
    try {
    for(int i=0; i<100; i++){
      for(int k=0; k<10; k++) {
        for (int j = 0; j < 10; j++) {
          System.out.print("\r" + i + ":" + k + j + "\r");
          Thread.sleep(10);
        }
      }
    }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    gameClearance();


  }

}
