import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpTest {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> {
      try {
        DatagramSocket ds = new DatagramSocket(10000);
        while (true) {
          byte[] buffer = new byte[1024];
          DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
          ds.receive(dp);
          System.out
              .print("收到来自[" + dp.getAddress().getHostAddress() + ":" + dp.getPort() + "]的信息：");
          System.out.println(new String(dp.getData(), 0, dp.getLength()));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    executorService.execute(() -> {
      try {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要发送的信息：");
        while (true) {
          String sendInfo = sc.nextLine();
          byte[] bytes = sendInfo.getBytes();
          DatagramPacket dp = new DatagramPacket(bytes, bytes.length,
              InetAddress.getByName("192.168.118.2"),
              10000);
          ds.send(dp);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    executorService.shutdown();

  }
}
