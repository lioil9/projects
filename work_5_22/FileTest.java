package club.banyuan;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
  private static final String PWD = "pwd";
  private static final String LS = "ls";
  private static final String CP = "cp";
  private static final String CAT = "cat";

  public static void main(String[] args) throws IOException {

    while (true) {
      String path = null;
      Scanner sc = new Scanner(System.in);
      System.out.print(":");
      path = sc.nextLine();
      File dir;
      if(PWD.equals(path)){
        dir = new File("./");
        System.out.println(dir.getCanonicalPath());
      }else if ("ls".equals(path)) {
        dir = new File("./");
      } else {
        dir = new File(path);
        String[] child = dir.list();
        if(child == null){
          System.out.println("目录为空");
        }else {
          int i = 0;
          for (String s : child) {
            System.out.printf("%-20s", s);
            i++;
            if(i==7){
              i = 0;
              System.out.println();
            }
          }
          System.out.println();
        }
      }
      }


  }

}
