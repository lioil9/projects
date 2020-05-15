package club.banyuan.product;

/**
 * 产品的抽象类模版
 */
abstract class Product {
    protected String name;
    protected int price;
    protected int leftNum;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
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
        if(price > 0 && price < 100){
            this.price = price;
        } else {
            throw new Exception();
        }

    }
}
