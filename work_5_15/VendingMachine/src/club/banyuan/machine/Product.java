package club.banyuan.machine;

import club.banyuan.exception.SetInputException;

/**
 * 要售卖商品类
 */
public class Product {
    private final String id;    // 商品id，初始化之后就确定，不能更改
    private String name;        // 商品名称
    private int price;          // 商品价格
    private int leftNum;        // 当前种类商品剩余数量
    private boolean status;     // 当前种类商品是否被购买
    private static final int INIT_NUM = 10;     // 填满商品的默认数量

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

    public void setName(String name) throws SetInputException {
        if (name.length() <= 20 && !name.contains(" ")) {
            this.name = name;
        } else {
            throw new SetInputException();
        }

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws SetInputException {
        if (price > 0 && price < 100) {
            this.price = price;
        } else {
            throw new SetInputException();
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
        status = leftCoin >= price;
    }

    public void refill() {
        leftNum = INIT_NUM;
    }

    @Override
    public String toString() {
        return id + ". " + name + " ($" + price + ") ";
    }
}
