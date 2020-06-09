package club.banyuan.tcp;

import club.banyuan.machine.Shelf;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 接收指令对服务端的Shelf进行操作，并发送给客户端相应的信息的线程
 */
public class ServerThread implements Runnable {

  private final Socket socket;
  private final Server server;


  public ServerThread(Socket socket, Server server) {
    this.socket = socket;
    this.server = server;
  }

  @Override
  public void run() {
    try {
      BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      while (!Thread.currentThread().isInterrupted()) {
        ObjectInputStream ois = new ObjectInputStream(bis);
        Command command = (Command) ois.readObject();
        switch (command){
          case UPDATE:
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(server.getShelves());
            oos.flush();
            bos.flush();
            break;
          case PURCHASE:
            ObjectOutputStream oos1 = new ObjectOutputStream(bos);
            Shelf purchase = (Shelf) ois.readObject();
            Boolean flag = server.purchaseProduct(purchase);
            oos1.writeObject(flag);
            oos1.flush();
            bos.flush();
            break;
          case PASSWORD:
            bos.write(server.getPassword().getBytes());
            bos.flush();
            break;
          case REFILL:
            Shelf purchase1 = (Shelf) ois.readObject();
            server.refillProduct(purchase1);
            break;
          case CHANGE:
            Shelf purchase2 = (Shelf) ois.readObject();
            server.changeProduct(purchase2);
            break;
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      System.out.printf("客户端[%s:%s]断开连接！\n", socket.getInetAddress().getHostAddress(),socket.getPort());
    }
  }


}
