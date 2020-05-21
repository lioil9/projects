package club.banyuan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 比较在基于数组的列表和基于链表的列表的末尾增加n个值所需的时间
 * <p>
 * 1.创建一个计时器。
 * <p>
 * 2.计算将n个值添加到空链表的时间：
 * a.创建一个空的链表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i添加到list中
 * d.停止计时器
 * e.显示时间
 * <p>
 * 3.计算将n个值添加到空数组列表的时间：
 * a.创建一个空的数组列表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i添加到list中
 * d.停止计时器
 * e.显示时间
 */
public class Append implements Timer{
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
    LinkedList<Integer> linkedList = new LinkedList<>();
    ArrayList<Integer> arrayList = new ArrayList<>();
    calTime("ArrayList", arrayList, 100000);
    calTime("LinkedList", linkedList, 100000);

  }

  public static void calTime(String name, List<Integer> list, int num){
    Timer timer = new Append();
    timer.start();
    for(int i=0; i<num; i++){
      list.add(i);
    }
    timer.stop();
    System.out.println(name+"末尾依次往后添加："+timer.getTimeMillisecond());
    timer.reset();
  }

}

