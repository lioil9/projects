package club.banyuan;

// TODO: 实现 Measurable 接口. getMeasure() 返回国家的人口总数. 提供构造方法，让DataSetTester运行正常
public class Country implements Measurable{
    private final int measure;

    public Country(int measure){
        this.measure = measure;
    }

    @Override
    public double getMeasure() {
        return measure;
    }


}
