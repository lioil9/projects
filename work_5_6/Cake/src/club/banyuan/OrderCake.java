package club.banyuan;

public class OrderCake extends Cake{
    private double weightInKG;

    public OrderCake(int id, double price, double weightInKG) {
        super(id, price);
        this.weightInKG = weightInKG;
    }

    public double getWeightInKG() {
        return weightInKG;
    }

    public void setWeightInKG(double weightInKG) {
        this.weightInKG = weightInKG;
    }

    /**
     * 重写toString方法，打印当前种类蛋糕，id，单价，重量
     * @return
     */
    @Override
    public String toString() {
        return "OderCake"+super.toString()+ "\t"+ weightInKG;
    }

    @Override
    public double calcPrice() {
        return price * weightInKG;
    }
}
