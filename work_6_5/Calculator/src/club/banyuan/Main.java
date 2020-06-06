package club.banyuan;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    Double result = null;
    String regex = "^(\\d+.\\d)+\\s<([\\+\\-\\*/%])>\\s(\\d+.\\d+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if(matcher.matches()){
      double p1 = Double.parseDouble(matcher.group(1));
      double p2 = Double.parseDouble(matcher.group(3));
      Operation operation  = Operation.getOperation(matcher.group(2));
      switch (operation){
        case ADD:
          result = p1 + p2;
          break;
        case SUB:
          result = p1 - p2;
          break;
        case MUL:
          result = p1 * p2;
          break;
        case DIV:
          result = p1 / p2;
          break;
        case MOD:
          result = p1 % p2;
      }
      System.out.println(matcher.group(0)+" = "+result);
    }else {
      System.out.println("输入错误!");
    }
  }

  public static double calculator(){

    return 0;
  }

}
