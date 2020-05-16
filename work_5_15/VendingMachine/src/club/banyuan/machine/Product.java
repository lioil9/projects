package club.banyuan.machine;

public class Product {
    private final String id;
    private String name;
    private int price;
    private int leftNum;
    private boolean status;
    private static final int INIT_NUM = 10;

    public Product(String id, String name, int price, int leftNum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.leftNum = leftNum;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws Exception {
        if (price > 0 && price < 100) {
            this.price = price;
        } else {
            throw new Exception();
        }
    }

    public String getId() {
        return id;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    public boolean isEmpty() {
        return leftNum == 0;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(int leftCoin) {
        if (leftCoin >= price) {
            status = true;
        } else {
            status = false;
        }
    }

    public void refill() {
        leftNum = INIT_NUM;
    }

    @Override
    public String toString() {
        return id + ". " + name + " ($" + price + ") ";
    }
}
