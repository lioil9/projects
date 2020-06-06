package club.banyuan;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
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
        ServerSocket serverSocket = new ServerSocket(socket.getLocalPort());
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

        System.out.println("请选择： 1.加密文件 2.解密文件");
        String select = sc.next();
        byte[] s = Arrays.copyOf(select.getBytes(), 1);
        bos.write(s);

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
        socket.close();

        System.out.println("等待接收文件。。。");
        Socket newSocket = serverSocket.accept();
        BufferedInputStream bis = new BufferedInputStream(newSocket.getInputStream());
        StringBuilder newFilePath = new StringBuilder(file.getAbsolutePath());
        if("1".equals(select)) {
          newFilePath.insert(newFilePath.indexOf(file.getName()), "Decode_");
        }else if ("2".equals(select)){
          newFilePath.insert(newFilePath.indexOf(file.getName()), "Encode_");
        }
        FileOutputStream fos = new FileOutputStream(new File(newFilePath.toString()));
        byte[] newBytes = new byte[1024];
        while (bis.read(newBytes) != -1){
          fos.write(newBytes);
        }
        fos.flush();
        bis.close();
        fos.close();
        System.out.println("1".equals(select)?"文件加密完成！":"文件解密完成！");
        break;
      } catch (IOException e) {
        e.printStackTrace();
        break;
      }
    }
  }

}
