import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpServer {

  public static void main(String[] args) throws IOException {
    DatagramSocket ds = new DatagramSocket(10000);
    Scanner input = new Scanner(System.in);

    System.out.println("本机为服务机，ip为"+InetAddress.getLocalHost().getHostAddress());

    while (true) {
//      ds.getReceiveBufferSize();
      byte[] buffer = new byte[1024];
      System.out.println("等待接收消息中。。。");
      DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
      ds.receive(dp);
      System.out.print("收到来自["+dp.getAddress().getHostAddress()+":"+dp.getPort()+"]的信息：");
      System.out.println(new String(dp.getData(), 0, dp.getLength()));

    }
//    ds.close();

  }
}
