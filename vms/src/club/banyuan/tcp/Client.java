package club.banyuan.tcp;

import java.io.IOException;
import java.net.Socket;

public class Client {

  public static void main(String[] args) {
    try(Socket socket = new Socket("192.168.9.92", 10000)) {
      System.out.println("连接成功");
      VendingMachineClient vendingMachineClient = new VendingMachineClient();
      vendingMachineClient.start(socket);


    }catch (IOException | ClassNotFoundException e){

    }

  }

}
