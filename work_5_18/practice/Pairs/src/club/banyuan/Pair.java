package club.banyuan;

public class Pair<T>{

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

}