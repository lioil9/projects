package club.banyuan.machine;

public enum Coin {
    One(1), Two(2), Five(5), Ten(10);
    private int price;

    Coin(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
