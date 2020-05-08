package club.banyuan;

public class LinkedList implements List {

  private Node head = new Node();
  private Node tail = head;
  private int size;

  @Override
  public Object set(int index, Object element) {
    if(isLegal(index))
      return null;
    Node cur = searchNode(index);
    Object temp = cur.getData();
    cur.setData(element);
    return temp;
  }

  @Override
  public Object get(int index) {
    if(isLegal(index))
      return null;
    Node cur = searchNode(index);
    return cur.getData();
  }

  @Override
  public void clear() {
    this.head = new Node();
    this.tail = head;
    size = 0;
  }

  @Override
  public Object remove(int index) {

    return null;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean add(Object o) {
    Node node = new Node();
    node.setData(o);
    node.setPrev(tail);
    tail.setNext(node);
    tail = node;
    size++;
    return true;
  }

  @Override
  public boolean isEmpty() {
    if(size == 0)
      return false;
    else
      return true;
  }

  @Override
  public int size() {
    return size;
  }

  //判断下标是否合法
  public boolean isLegal(int index){
    if (index >= size) {
      System.out.println("下标不合法");
      return true;
    } else {
      return false;
    }
  }
  //通过下标查找到结点
  public Node searchNode(int index){
    int count = index;
    Node cur = head;
    while (count != -1) {
      cur = cur.getNext();
      count--;
    }
    return cur;
  }
}
