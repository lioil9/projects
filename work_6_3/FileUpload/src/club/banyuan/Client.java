package club.banyuan;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int count = 0;
      double sumCount = 0;
      try (Socket socket = new Socket("127.0.0.1", 10000)) {
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        System.out.println("请输入要发送的文件地址：");
        String filePath = sc.next();
        if("quit".equalsIgnoreCase(filePath)){
          break;
        }

        File file = new File(filePath);
        if (!file.exists()) {
          System.out.println("不存在此文件，请重新输入！");
          continue;
        }

        byte[] info = Arrays.copyOf(file.getName().getBytes(), 256);
        bos.write(info);

        FileInputStream fis = new FileInputStream(file);

        byte[] bytes = new byte[1024];
        count = fis.read(bytes);
        while (count != -1) {
          sumCount += count;
          bos.write(bytes);
          System.out.printf("\r传输进度：%.2f%%", sumCount / file.length() * 100);
          count = fis.read(bytes);
        }
        bos.flush();
        System.out.println("\n" + file.getName() + " 文件发送完毕");
        fis.close();
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
