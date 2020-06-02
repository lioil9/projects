import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UdpTest {

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    Client client = new Client();

    server.start();
    client.start();

  }
}

class Server extends Thread {

  DatagramSocket ds = new DatagramSocket(10001);

  Server() throws IOException {
  }

  @Override
  public void run() {
    while (true) {
      byte[] buffer = new byte[1024];
//      System.out.println("等待接收消息中。。。");
      DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
      try {
        ds.receive(dp);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.print("收到来自[" + dp.getAddress().getHostAddress() + ":" + dp.getPort() + "]的信息：");
      System.out.println(new String(dp.getData(), 0, dp.getLength()));
    }
  }
}

class Client extends Thread {

  DatagramSocket ds = new DatagramSocket();

  Client() throws SocketException {
  }

  @Override
  public void run() {
    Scanner sc = new Scanner(System.in);
    System.out.print("请输入要发送的信息：");
    while (true) {
      String sendInfo = sc.nextLine();
      byte[] bytes = sendInfo.getBytes();
      DatagramPacket dp = null;
      try {
        dp = new DatagramPacket(bytes, bytes.length,
            InetAddress.getByName("192.168.9.92"),
            10002);
        ds.send(dp);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }
}