package club.banyuan;

public class ListTest {

  public static void main(String[] args) {
    List list = new LinkedList();
    list.add(12);
    list.add(null);
    list.add(15);
    printList(list);

     System.out.println(list.size() == 2);
     System.out.println(list.get(1) == Integer.valueOf(12));
     System.out.println(list.remove(null));
     printList(list);
     System.out.println(list.remove(Integer.valueOf(12)));
     System.out.println((Integer) list.remove(0) == 15);
     System.out.println(list.isEmpty());
     list.add(22);
     list.add(33);
     list.add(44);
     printList(list);
     System.out.println(list.size() == 3);
     System.out.println((Integer) list.set(2, 55) == 44);
     list.clear();
     System.out.println(list.size() == 0);

      List list1 = new ArrayList();
      list1.add(12);
      list1.add(null);
      list1.add(15);
      list1.add(null);
      printList(list1);

      System.out.println(list1.size() == 2);
      System.out.println(list1.get(1) == Integer.valueOf(12));
      System.out.println(list1.remove(null));
      printList(list1);
      System.out.println(list1.remove(Integer.valueOf(12)));
      System.out.println((Integer) list1.remove(0) == 15);
      System.out.println(list1.isEmpty());
      list1.add(22);
      list1.add(33);
      list1.add(44);
      printList(list1);
      System.out.println(list1.size() == 3);
      System.out.println((Integer) list1.set(2, 55) == 44);
      list1.clear();
      System.out.println(list1.size() == 0);


  }

  public static void printList(List list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println("[" + i + "]=" + list.get(i));
    }
  }
}
