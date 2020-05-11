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

  //重载方法，可以调用Country类中实现的getMeasure方法
  public void add(Country c) {
    sum = sum + c.getMeasure();
    Country max = (Country)maximum;
    if (count == 0 ||  max.getMeasure() < c.getMeasure()) {
      maximum = c;
    }
    count++;
  }

  public double getAverage() {
    // TODO: Check divide by zero. Compute the average value.
    return sum/count;
  }

  public Object getMaximum() {
    return maximum;
  }


}