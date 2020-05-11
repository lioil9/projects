package club.banyuan;

public class MapTest {

  public static void main(String[] args) {
    Map map = new HashMap();
    String
    map.put("01","abc");
    map.put("02","bcd");
    map.put("03","efg");
    map.put("04","hij");
    map.put("05",null);
    System.out.println(map.size());
    System.out.println(map.get("01"));
    System.out.println(map.get("02"));
    System.out.println(map.containsKey("03"));
    System.out.println(map.containsValue("abc"));
    map.remove("01");
    System.out.println(map.size());//2
    map.remove("02");
    map.remove("04");
    System.out.println(map.containsValue(null));
    System.out.println(map.containsKey("02"));
    System.out.println(map.get("03"));
    map.remove("03");
    map.remove("03");
    System.out.println(map.size());
    System.out.println(map.containsValue(null));
    System.out.println(map.isEmpty());
  }
}
