
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

  public static void main(String[] args) throws IOException {
    DatagramSocket ds = new DatagramSocket();
    Scanner sc = new Scanner(System.in);

    System.out.println("本机为客户机，ip为" + InetAddress.getLocalHost().getHostAddress());

    while (true) {
      System.out.print("请输入要发送的信息：");
      String sendInfo = sc.nextLine();
      byte[] bytes = sendInfo.getBytes();
      DatagramPacket dp = new DatagramPacket(bytes, bytes.length,
          InetAddress.getByName("192.168.9.92"),
          10000);
      ds.send(dp);
    }
//    ds.close();
  }
}