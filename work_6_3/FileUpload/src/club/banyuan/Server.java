package club.banyuan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(10000)) {
      System.out.println("等待接收文件。。。");
      Socket socket = serverSocket.accept();
      String hostAddress = socket.getInetAddress().getHostAddress();
      System.out.println("接收到[" + hostAddress + "]发来的文件!");

      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

      byte[] info = new byte[256];
      bis.read(info);
      StringBuilder fileName = new StringBuilder(new String(info).trim());
      File file = new File("./" + fileName);

      while (file.exists()){
        fileName.insert(fileName.lastIndexOf("."), 1);
        file = new File("./"+fileName);
        System.out.println("当前路径已存在同名文件，");
      }

      FileOutputStream fos = new FileOutputStream(file);
      byte[] bytes = new byte[1024];
      while (bis.read(bytes) != -1) {
        fos.write(bytes);
      }
      fos.flush();

      System.out.println(fileName + " 文件接收完毕,文件保存在: " + file.getAbsolutePath()+" )");

      fos.close();
      bis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
