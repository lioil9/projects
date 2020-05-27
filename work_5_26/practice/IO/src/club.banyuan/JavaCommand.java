package club.banyuan;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class JavaCommand {

  private static final String LS = "ls";
  private static final String CP = "cp";
  private static final String RM = "rm";
  private static final String CAT = "cat";

  public static void main(String[] args) {
    if (args.length == 0) {
      return;
    }
    if (LS.equalsIgnoreCase(args[0])) {
      commandLs(args);
    } else if (CP.equalsIgnoreCase(args[0])) {
      commandCp(args);
    } else if (RM.equalsIgnoreCase(args[0])) {
      commandRm(args);
    } else if (CAT.equalsIgnoreCase(args[0])) {
      commandCat(args);
    } else {
      System.out.println("输入不合法！");
    }

  }

  public static void printDir(String[] child) {
    int i = 0;
    for (String s : child) {
      i++;
      System.out.printf("%-20s%3s", s, "");
      if (i == 7) {
        i = 0;
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void printDetail(File[] child) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (File file : child) {
      System.out.printf("%-16s", file.isDirectory() ? "d" : "-");
      System.out.printf("%-16s", file.isFile() ? file.length() : "0");
      System.out.printf("%-24s", dateFormat.format(file.lastModified()));
      System.out.printf("%s\n", file.getName());
    }
  }

  public static void commandLs(String[] args) {
    switch (args.length) {
      case 1:
        String[] file = new File("./")
            .list((dir, name) -> !name.substring(0, 1).contains("."));
        if (file != null) {
          printDir(file);
        } else {
          System.out.println("当前目录为空");
        }
        break;
      case 2:
        if (args[1].equalsIgnoreCase("-l")) {
          File[] child = new File("./")
              .listFiles((dir, name) -> !name.substring(0, 1).contains("."));
          if (child != null) {
            printDetail(child);
          } else {
            System.out.println("当前目录为空");
          }
        } else {
          String[] child = new File(args[1])
              .list((dir, name) -> !name.substring(0, 1).contains("."));
          if (child != null) {
            printDir(child);
          } else {
            System.out.println("目录为空或不存在此目录");
          }
        }
        break;
      case 3:
        if (args[2].equalsIgnoreCase("-l")) {
          File[] child = new File(args[1])
              .listFiles((dir, name) -> !name.substring(0, 1).contains("."));
          if (child != null) {
            printDetail(child);
          } else {
            System.out.println("当前目录为空");
          }
        } else {
          System.out.println("输入不合法");
        }
        break;
      default:
        System.out.println("输入不合法");
    }
  }

  public static void commandCp(String[] args) {
    if (args.length == 3) {
      File oldFile = new File(args[1]);
      File newFile = new File(args[2] + File.separator + oldFile.getName());
      if (!oldFile.isFile() && !oldFile.exists()) {
        System.out.println("原路径不是文件或不存在");
        return;
      }
      if (newFile.exists()) {
        System.out.println("当前路径文件已存在");
        return;
      }
//      else {
//        try {
//          newFile.createNewFile();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
      try {
        FileInputStream fileIn = new FileInputStream(oldFile);
        FileOutputStream fileOut = new FileOutputStream(newFile);
        byte[] b = new byte[1024];
        int count = 0;
        double sumCount = 0;
        try {
//          do {
//            count = fileIn.read(b);
//            fileOut.write(b);
//            System.out.println(count);
//          } while (count != -1);
          count = fileIn.read(b);
          while (count != -1) {
            sumCount += count;
            fileOut.write(b);
            System.out.printf("%.2f%%\n",sumCount/oldFile.length()*100);
            count = fileIn.read(b);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

    } else {
      System.out.println("输入不合法");
    }
  }

  public static void commandRm(String[] args) {
    if (args.length == 2) {
      File file = new File(args[1]);
      if (!file.exists()) {
        System.out.println("文件或目录不存在");
      } else {
        deleteDir(file);
      }
    } else {
      System.out.println("输入不合法！");
    }
  }

  public static void deleteDir(File file) {
    if (file.listFiles() != null) {
      for (File listFile : file.listFiles()) {
        if (file.isDirectory()) {
          deleteDir(listFile);
        } else {
          listFile.delete();
        }
      }
    }
    file.delete();
  }

  public static void commandCat(String[] args) {
    if (args.length == 2) {
      File file = new File(args[1]);
      if (file.exists() && file.isFile()) {
        try {
          FileReader fr = new FileReader(file);
          BufferedReader br = new BufferedReader(fr);
          String out = br.readLine();
          while (out != null) {
            System.out.println(out);
            out = br.readLine();
          }
          fr.close();
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("路径不为文件或文件不存在！");
      }
    } else {
      System.out.println("输入不合法");
    }


  }
}