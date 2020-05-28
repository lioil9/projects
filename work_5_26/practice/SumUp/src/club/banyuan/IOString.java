package club.banyuan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class IOString {

  public static String[] loadArray(InputStream is, int[] n) {
    String[] str = new String[n.length];
    for (int i = 0; i < n.length; i++) {
      byte[] b = new byte[n[i]];
      try {
        if (is.read(b) != i) {
          throw new RuntimeException();
        } else {
          str[i] = new String(b);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    try {
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static void saveArray(OutputStream os, String[] sa) {
    for (String s : sa) {
      try {
        os.write(s.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeTables(String path, int n) {
    File file = new File(path);
    if (file.isFile() && file.exists()) {
      try {
        FileOutputStream outFile = new FileOutputStream(file);
        for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= n; j++) {
            String str = i * j + " ";
            outFile.write(str.getBytes());
          }
          outFile.write("\n".getBytes());
        }
        outFile.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("原路径不是文件或不存在");
    }
  }

  public static void main(String[] args) {
//    writeTables("test.txt", 10);
    File file = new File("test.txt");
    try {
      int n[] = {1,2,3,4,9};
      InputStream is = new FileInputStream(file);
      System.out.println(Arrays.toString(loadArray(is, n)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

}
