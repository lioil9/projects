package club.banyuan;

public class RectangleMeasurer implements Measurer{
    // TODO: 创建实现Measurer接口的RectangleMeasurer类。
    //  提示：RectangleMeasurer类应包含具有与接口中指定的签名相同的方法。
    //  measure()应该返回矩形的面积。

    @Override
    public double measure(Object anObject) {
        Rectangle rectangle = (Rectangle)anObject;
        return (rectangle.height*rectangle.width);
    }

}
