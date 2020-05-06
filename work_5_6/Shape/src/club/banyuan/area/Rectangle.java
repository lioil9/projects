package club.banyuan.area;

public class Rectangle extends Shape{
    private int l;
    private int w;

    public Rectangle(int l, int w) {
        this.l = l;
        this.w = w;
    }

    @Override
    public double area() {
        return l * w;
    }

    @Override
    public double perimeter() {
        return 2 * (l+w);
    }

    @Override
    public String getShapeName() {
        return "矩形";
    }
}
