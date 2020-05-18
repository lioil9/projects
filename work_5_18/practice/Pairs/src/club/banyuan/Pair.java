package club.banyuan;

public class Pair<T> implements Comparable{

  private T first;
  private T second;

  public Pair(T firstElement, T secondElement) {
    first = firstElement;
    second = secondElement;
  }

  public Object getFirst() {
    return first;
  }

  public Object getSecond() {
    return second;
  }

  public void swap(){
    T temp = first;
    first = second;
    second = temp;
  }
  public static <T> Pair swap(Pair<T> pair){
    pair.swap();
    return pair;
  }
  public Pair minmax(){

    return this;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}