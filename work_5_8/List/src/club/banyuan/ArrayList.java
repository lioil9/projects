package club.banyuan;

public class ArrayList implements List {
  private static final int DEFAULT_CAPACITY = 20;
  private int size;

  @Override
  public Object set(int index, Object element) {
    return null;
  }

  @Override
  public Object get(int index) {
    if(index >= size){
      System.out.println("下标不合法");
      return null;
    }
    return null;
  }

  @Override
  public void clear() {

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
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int size() {
    return size;
  }
}
