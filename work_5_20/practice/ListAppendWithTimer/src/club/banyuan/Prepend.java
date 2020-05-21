package club.banyuan;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 比较在基于数组的列表和基于链表的列表的第一个元素前插入n个值所需的时间
 * <p>
 * 1.创建一个计时器。
 * <p>
 * 2.计算将n个值添加到空链表的时间：
 * a.创建一个空的链表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i插入到list中
 * d.停止计时器
 * e.显示时间
 * <p>
 * 3.计算将n个值添加到空数组列表的时间：
 * a.创建一个空的数组列表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i插入到list中
 * d.停止计时器
 * e.显示时间
 */
public class Prepend implements Timer {
  private long startTime;
  private long stopTime;
  private long time;
  private boolean stopStatus = false;
  private boolean startStatus = false;

  @Override
  public void start() throws IllegalStateException {
    if (startStatus && !stopStatus){
      throw new IllegalStateException();
    }
    time = 0;
    startStatus = true;
    startTime = System.currentTimeMillis();
  }

  @Override
  public void stop() throws IllegalStateException {
    if(!startStatus){
      throw new IllegalStateException();
    }
    stopStatus = true;
    stopTime = System.currentTimeMillis();
    time = stopTime - startTime;
  }

  @Override
  public void reset() {
    time = 0;
  }

  @Override
  public long getTimeMillisecond() {
    return time;
  }

  public static void main(String[] args) {
    Timer timer = new Prepend();
    LinkedList<Integer> linkedList = new LinkedList<>();
    timer.start();
    for(int i=0; i<100000; i++){
      linkedList.add(0,i);
    }
    timer.stop();
    System.out.println("LinkedList在第一个元素前添加："+timer.getTimeMillisecond());
    timer.reset();

    ArrayList<Integer> arrayList = new ArrayList<>();
    timer.start();
    for(int i=0; i<100000; i++){
      arrayList.add(0,i);
    }
    timer.stop();
    System.out.println("ArrayList在第一个元素前添加："+timer.getTimeMillisecond());
  }

}