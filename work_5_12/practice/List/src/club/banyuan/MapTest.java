package club.banyuan;
import club.banyuan.util.map.Map;
import club.banyuan.util.map.HashMap;

public class MapTest {

  public static void main(String[] args) {
    Map map = new HashMap();
    String str = new String("123");
    String str1 = new String("123");
    map.put(str, str);
    map.put("abc","bcd");
    map.put("abc","bcd");
    map.put("abc","bcd");
    map.put("abc","123456");
    System.out.println(map.size());
    System.out.println(map.get(str1));
    System.out.println(map.get("abc"));
  }
}
