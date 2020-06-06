package club.banyuan;

import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    Pattern p1 = Pattern.compile("^[0-1]*$");
    Pattern p2 = Pattern.compile("^[0-1]*0$");
    Pattern p3 = Pattern.compile("^(11|00|10|01)*$");
    Pattern p4 = Pattern.compile("[10]*(0110|1001)[10]*");
    Pattern p5 = Pattern.compile("^[10]*(100110)[10]*|[10]*(011001)[10]*|([10]*(0110|1001)[10]*(0110|1001)[10]*)$");
    Pattern p6 = Pattern.compile("");
    Pattern p7 = Pattern.compile("(pick)(\\s|-)?(up)\\s(truck)");
    Pattern p8 = Pattern.compile("(\\S+\\s){2}\\S+(\\s\\S*)?");
    Pattern p9 = Pattern.compile("^((2[0-3])|(1[0-9])|([0-9])):[0-5]\\d$");
    Pattern p10 = Pattern.compile("(ATG)([ACGT]{3})+(TAA|TAG|TGA)$");
    Pattern p11 = Pattern.compile("^\\$(\\d{1,3})((\\d*)|(,\\d{3})*)(\\.\\d{2})?$");
    Pattern p12 = Pattern.compile("");



  }

}
