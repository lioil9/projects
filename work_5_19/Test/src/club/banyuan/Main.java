package club.banyuan;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String str = "134f333rgdadafr34";
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (char c : chars) {
            if(map.get(c) == null){
                map.put(c,1);
            }else {
                Integer value = map.get(c);
                map.put(c,++value);
            }
        }
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character,Integer> entry = (Map.Entry<Character,Integer>) iterator.next();
            System.out.printf("%s:%d\n", entry.getKey(),entry.getValue());
        }
        System.out.println();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.printf("%s:%d\n",entry.getKey(),entry.getValue());
        }
    }
}
