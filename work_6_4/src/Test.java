import java.io.FileNotFoundException;
import java.util.regex.Pattern;

class Test {

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(add("1", "99"));
    String s1 = "hello";
    String s2 = "he" + "llo";
    String s3 = "he" + new String("llo");
    System.out.println(s1==s2);
    System.out.println(s1==s3);
    String r = "^\\-?\\d+\\.?\\d*$";
    Pattern.matches(r,s1);


  }

  public static String add(String a1, String a2) {
    char[] big;
    char[] small;
    int count = 0;
    int left = 0;
    StringBuilder result = new StringBuilder();
    if (a1.length() > a2.length()) {
      big = a1.toCharArray();
      small = a2.toCharArray();
    } else {
      big = a2.toCharArray();
      small = a1.toCharArray();
    }
    int j = big.length - 1;
    for (int i = small.length - 1; i >= 0; i--) {
      left = ((small[i] - '0') + (big[j] - '0') + count) % 10;
      count = ((small[i] - '0') + (big[j] - '0') + count) / 10;
      result.insert(0, left);
      j--;
    }
    for (int k = j; k >= 0; k--) {
      left = ((big[k] - '0') + count) % 10;
      count = ((big[k] - '0') + count) / 10;
      result.insert(0, left);
    }
    if (count > 0) {
      result.insert(0, count);
    }

    return result.toString();
  }
}

