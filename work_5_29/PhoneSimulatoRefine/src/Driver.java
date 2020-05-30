public class Driver {

  public static void main(String[] args) {
    // 创建一个电话
    CellularPhone aPhone = new CellularPhone("Phone");
    aPhone.start();

    // 创建两个呼叫生成器，他们坚持不断地给电话打电话。
    PhoneCallGenerator pcGen1 = new PhoneCallGenerator("Tom", aPhone);
    PhoneCallGenerator pcGen2 = new PhoneCallGenerator("Jerry", aPhone);
    MessageGenerator msGen1 = new MessageGenerator("Mother", aPhone);
    MessageGenerator msGen2 = new MessageGenerator("Father", aPhone);
    MessageGenerator msGen3 = new MessageGenerator("Brother", aPhone);
    MessageGenerator msGen4 = new MessageGenerator("Sister", aPhone);
    pcGen1.start();
    pcGen2.start();
    msGen1.start();
    msGen2.start();
    msGen3.start();
    msGen4.start();

    try {
      // 等待，直到所有的电话都结束
      pcGen1.join();
      pcGen2.join();
      // 停止电话
      aPhone.stopPhone();
    } catch (InterruptedException ie) {
      // 必须要处理的join抛出的异常
      System.exit(1);
    }
  }
}
