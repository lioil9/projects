package club.banyuan.tcp;

import club.banyuan.machine.Shelf;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

  private final Shelf[] shelves = new Shelf[SHELVES_NUM];
  public static final int SHELVES_NUM = 5;
  private static final String password = "1110";
  public static final int FULL_INVENTORY = 10;

  public Shelf[] getShelves() {
    return shelves;
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


  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(10000)) {
      Socket socket = serverSocket.accept();
      System.out.println("已有客户端接入");
      Server server = new Server();
      server.initShelves();
      BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      while (true) {
        byte[] bytes = new byte[1];
        bis.read(bytes);
        if (Arrays.equals("0".getBytes(), bytes)) {
          ObjectOutputStream oos = new ObjectOutputStream(bos);
          oos.writeObject(server.getShelves());
          oos.flush();
          bos.flush();
        }
        if (Arrays.equals("1".getBytes(), bytes)) {
          ObjectInputStream ois = new ObjectInputStream(bis);
          ObjectOutputStream oos = new ObjectOutputStream(bos);
          Shelf purchase = (Shelf) ois.readObject();
          Boolean flag = server.purchaseProduct(purchase);
          oos.writeObject(flag);
          oos.flush();
          bos.flush();
        }
        if (Arrays.equals("2".getBytes(), bytes)) {
          bos.write(Server.password.getBytes());
          bos.flush();
        }
        if (Arrays.equals("3".getBytes(), bytes)) {
          ObjectInputStream ois = new ObjectInputStream(bis);
          Shelf purchase = (Shelf) ois.readObject();
          server.refillProduct(purchase);
        }
        if (Arrays.equals("4".getBytes(), bytes)) {
          ObjectInputStream ois = new ObjectInputStream(bis);
          Shelf purchase = (Shelf) ois.readObject();
          server.changeProduct(purchase);
        }
      }

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void changeProduct(Shelf purchase) {
    for (int i = 0; i < shelves.length; i++) {
      if (shelves[i].getCode().equals(purchase.getCode())) {
        shelves[i] = purchase;
      }
    }
  }


  private Boolean purchaseProduct(Shelf purchase) {
    for (Shelf shelf : shelves) {
      if (shelf.getCode().equals(purchase.getCode()) && shelf.getInventory() > 0) {
        shelf.setInventory(purchase.getInventory() - 1);
        return true;
      }
    }
    return false;

  }

  private void refillProduct(Shelf purchase) {
    for (Shelf shelf : shelves) {
      if (shelf.getCode().equals(purchase.getCode())) {
        shelf.setInventory(FULL_INVENTORY);
      }
    }

  }

}
