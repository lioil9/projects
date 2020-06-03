import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

  public static void main(String[] args) {
    try(ServerSocket serverSocket = new ServerSocket(10000)){
      Socket socket = serverSocket.accept();
      String host = socket.getInetAddress().getHostAddress();
      System.out.println(host+"接入");

      InputStream inputStream = socket.getInputStream();
      byte[] bytes = inputStream.readAllBytes();
      System.out.println(new String(bytes));

    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
