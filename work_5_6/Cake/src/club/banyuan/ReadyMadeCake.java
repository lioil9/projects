package club.banyuan;

public class ReadyMadeCake extends Cake{
    private int quantity;

    public ReadyMadeCake(int id, double price, int quantity) {
        super(id, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 重写toString方法，打印当前种类蛋糕，id，单价，数量
     * @return
     */
    @Override
    public String toString() {
        return "ReadyMadeCake" + super.toString() + "\t"+ quantity;
    }

    @Override
    public double calcPrice() {
        return price * quantity;
    }
}
