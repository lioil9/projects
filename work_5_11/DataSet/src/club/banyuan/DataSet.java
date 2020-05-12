package club.banyuan;

public class DataSet{
  // TODO: 定义private 实例变量
  private double sum;
  private int count;
  private  Object maximum;
  private Measurer measurer;

  public DataSet(Measurer aMeasurer) {
    sum = 0;
    count = 0;
    maximum = null;
    measurer = aMeasurer;
  }
  //创建无参构造方法
  public DataSet(){
  }


  // TODO： 重载方法，使得Contry统计 DataSetTester 运行成功
  public void add(Object x) {
    sum = sum + measurer.measure(x);
    if (count == 0 || measurer.measure(maximum) < measurer.measure(x)) {
      maximum = x;
    }
    count++;
  }

  //重载方法，可以调用实现Measurable接口的类
  public void add(Measurable m) {
    sum = sum + m.getMeasure();
    Measurable max = (Measurable)maximum;
    if (count == 0 ||  max.getMeasure() < m.getMeasure()) {
      maximum = m;
    }
    count++;
  }

  public double getAverage() {
    // TODO: Check divide by zero. Compute the average value.
    if(count == 0){
      System.out.println("对象个数为0，无法计算");
      return 0;
    }
    return sum/count;
  }

  public Object getMaximum() {
    return maximum;
  }


}