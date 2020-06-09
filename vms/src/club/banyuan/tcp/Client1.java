package club.banyuan.tcp;

import java.io.IOException;
import java.net.Socket;

public class Client1 {

  public static void main(String[] args) {
    try(Socket socket = new Socket("127.0.0.1", 10000)) {
      System.out.println("连接成功");
      VendingMachineClient vendingMachineClient = new VendingMachineClient();
      vendingMachineClient.start(socket);

    }catch (IOException | ClassNotFoundException e){
      System.out.println("服务端已断开连接！");
    }

  }

}
