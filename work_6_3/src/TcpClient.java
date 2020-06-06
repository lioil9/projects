import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

  private int a = 0;

  public static void main(String[] args) {

    try(Socket socket = new Socket("127.0.0.1", 10000)){

      OutputStream out = socket.getOutputStream();
      out.write("connect".getBytes());

    }catch (IOException ie){
      ie.printStackTrace();
    }

  }
}
