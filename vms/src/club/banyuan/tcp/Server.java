package club.banyuan.tcp;

import club.banyuan.machine.Shelf;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端，可保存Shelf货架，并用线程池接收多个客户端
 */
public class Server {

  private final Shelf[] shelves = new Shelf[SHELVES_NUM];
  private static final int SHELVES_NUM = 5;
  private static final String password = "1110";
  private static final int FULL_INVENTORY = 10;
  private static final String QUIT = "quit";

  public Shelf[] getShelves() {
    return shelves;
  }

  public String getPassword() {
    return password;
  }

  private void initShelves() {
  /*
  初始化产品列表
  A. Juice ($10) (5 left)
  B. Cola($6)(1left)
  C. Tea ($5) (2 left)
  D. Water ($8) (1 left)
  E. Coffee ($7) (9 left)
   */
    shelves[0] = new Shelf("A", "Juic", 10, 5);
    shelves[1] = new Shelf("B", "Cola", 6, 1);
    shelves[2] = new Shelf("C", "Tea", 5, 2);
    shelves[3] = new Shelf("D", "Water", 8, 1);
    shelves[4] = new Shelf("E", "Coffee", 7, 9);
  }

  /**
   * 可提交接收而来的多个客户端线程，并且服务端输入quit指令时服务端结束运行；
   * @param args
   */
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Server server = new Server();
    server.initShelves();
    try (ServerSocket serverSocket = new ServerSocket(10000)) {
      executorService.submit(() -> {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (QUIT.equalsIgnoreCase(input)) {
          try {
            serverSocket.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
      while (!serverSocket.isClosed()) {
        System.out.println("等待接入客户端。。。(输入quit可关闭服务端)");
        Socket socket = serverSocket.accept();
        String host = socket.getInetAddress().getHostAddress();
        System.out.printf("客户端[%s:%s]接入\n", host, socket.getPort());
        ServerThread serverThread = new ServerThread(socket, server);
        executorService.submit(serverThread);
      }
    } catch (IOException e) {
      System.out.println("服务端结束运行！");
//      e.printStackTrace();
    }
    executorService.shutdownNow();
  }

  public void changeProduct(Shelf purchase) {
    for (int i = 0; i < shelves.length; i++) {
      if (shelves[i].getCode().equals(purchase.getCode())) {
        shelves[i] = purchase;
      }
    }
  }

  public Boolean purchaseProduct(Shelf purchase) {
    for (Shelf shelf : shelves) {
      if (shelf.getCode().equals(purchase.getCode()) && shelf.getInventory() > 0) {
        shelf.setInventory(purchase.getInventory() - 1);
        return true;
      }
    }
    return false;
  }

  public void refillProduct(Shelf purchase) {
    for (Shelf shelf : shelves) {
      if (shelf.getCode().equals(purchase.getCode())) {
        shelf.setInventory(FULL_INVENTORY);
      }
    }
  }

}
