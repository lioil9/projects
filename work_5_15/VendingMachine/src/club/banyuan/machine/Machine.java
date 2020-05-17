package club.banyuan.machine;

public class Machine {
    private int leftCoin;
    private int revenue;
    private String beBuyingProduct;
    private Product[] products = new Product[5];

    public Machine() {
    }


    public void initMachine() {
        leftCoin = 0;
        revenue = 0;
        beBuyingProduct = "=";
        products[0] = new Product("A", "Juice", 10, 5);
        products[1] = new Product("B", "Cola", 6, 1);
        products[2] = new Product("C", "Tea", 5, 2);
        products[3] = new Product("D", "Water", 8, 1);
        products[4] = new Product("E", "Coffee", 7, 9);
    }

    /**
     * 向售货机投钱
     *
     * @param coin 输入硬币枚举类对象
     */
    public void insertCoin(Coin coin) {
        leftCoin += coin.getPrice();
    }

    public int getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(int leftCoin) {
        this.leftCoin = leftCoin;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getBeBuyingProduct() {
        return beBuyingProduct;
    }

    public void setBeBuyingProduct(String beBuyingProduct) {
        this.beBuyingProduct = beBuyingProduct;
    }

    public void initBeBuyingProduct() {
        beBuyingProduct = "=";
    }

    /**
     * 设置显示剩余金额是否能够购买商品
     */
    public void productStatus() {
        for (Product product : products) {
            product.setStatus(leftCoin);
        }
    }

    /**
     * 购买商品
     *
     * @param product
     * @throws Exception
     */
    public void buyProduct(Product product) throws Exception {
        if (!product.isEmpty() && product.getPrice() <= leftCoin) {
            int temp = product.getLeftNum();
            product.setLeftNum(--temp);
            revenue += product.getPrice();
            leftCoin -= product.getPrice();
        } else {
            System.out.println("invalid choice");
            throw new Exception();
        }
    }

    /**
     * 提取所有余额
     *
     * @return 机器余额
     */
    public int withdrawMoney() {
        int sum = leftCoin + revenue;
        leftCoin = 0;
        revenue = 0;
        return sum;
    }

    public int returnLefCoin() {
        int temp = leftCoin;
        leftCoin = 0;
        return temp;
    }

    /**
     * 选择商品
     *
     * @return
     */
    public Product selectProduct(int i) {
        if (i > 0 && i <= products.length) {
            return products[i - 1];
        }
        return null;
    }

    public void displayProduct() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * 显示机器状态
     */
    public void machineStatus() {
        System.out.println("Amount of revenue: $" + revenue);
        System.out.println("Amount of inserted coins: $" + leftCoin);
        for (Product product : products) {
            if (product.isEmpty()) {
                System.out.println(product + "(sold out)");
            } else {
                System.out.println(product + "(" + product.getLeftNum() + " left)");
            }
        }
    }

    public int getLength() {
        return products.length;
    }

}
