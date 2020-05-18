package club.banyuan;

import java.util.Iterator;

public class PrintElement {

    public static <T extends Iterable<T>> void print(T t){
        Iterator<T> iterator = t.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }
        System.out.println();

    }
}
