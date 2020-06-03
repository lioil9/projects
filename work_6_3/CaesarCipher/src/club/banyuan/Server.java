package club.banyuan;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

  public static final int FIRST_UPPER = 65;
  public static final int FIRST_LOWER = 97;
  public static final int NUM_CHARS = 26;
  public static final int OFFSET = 3;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(10000)) {
      System.out.println("等待接收文件。。。");
      Socket socket = serverSocket.accept();
      String hostAddress = socket.getInetAddress().getHostAddress();
      System.out.println("[" + hostAddress + "]已连接");

      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

      byte[] s = new byte[1];
      bis.read(s);

      Socket newSocket = new Socket(socket.getInetAddress().getHostAddress(), socket.getPort());
      BufferedOutputStream bos = new BufferedOutputStream(newSocket.getOutputStream());
      byte[] bytes = new byte[1024];
      System.out.println("接收到文件！");
      while (bis.read(bytes) != -1) {
        StringBuilder temp = new StringBuilder();
        for (char c : new String(bytes).toCharArray()) {
          if(Arrays.equals("1".getBytes(), s)){
            temp.append(caesarEncode(c));
          }else if(Arrays.equals("2".getBytes(), s)){
            temp.append(caesarDecode(c));
          }
        }
        bos.write(temp.toString().getBytes());
      }
      System.out.println(Arrays.equals("1".getBytes(), s)?"文件加密完成，已发送！":"文件解密完成，已发送！");
      bos.flush();
      bos.close();
      bis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static char caesarDecode(char ch) {
    if (Character.isUpperCase(ch)) {
      return (char) ((ch - FIRST_UPPER + NUM_CHARS - OFFSET) % NUM_CHARS
          + FIRST_UPPER);
    } else if (Character.isLowerCase(ch)) {
      return (char) ((ch - FIRST_LOWER + NUM_CHARS - OFFSET) % NUM_CHARS
          + FIRST_LOWER);
    } else {
      return ch;
    }
  }

  public static char caesarEncode(char ch) {
    if (Character.isUpperCase(ch)) {
      return (char) ((ch - FIRST_UPPER + OFFSET) % NUM_CHARS + FIRST_UPPER);
    } else if (Character.isLowerCase(ch)) {
      return (char) ((ch - FIRST_LOWER + OFFSET) % NUM_CHARS + FIRST_LOWER);
    } else {
      return ch;
    }

  }

}
