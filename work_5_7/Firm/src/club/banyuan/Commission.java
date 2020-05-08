package club.banyuan;
/**
 * 定义可以赚取佣金的小时工
 */
public class Commission extends Hourly{
    private double totalSales;
    private double commissionRate;

    // 相比小时工添加佣金率，并且在构造时将销售额初始化为0
    public Commission(String eName, String eAddress, String ePhone, String socSecNumber, double rate, double commissionRate) {
        super(eName, eAddress, ePhone, socSecNumber, rate);
        this.commissionRate = commissionRate;
        totalSales = 0;
    }
    // 添加销售额
    public void addSales(double totalSales){
        this.totalSales += totalSales;
    }

    // 计算加上销售佣金支付的总工资，并且在支付完工资后，销售额清零，工作小时数已经在父类中清零
    @Override
    public double pay() {
        double payment =  super.pay() + totalSales*commissionRate;
        totalSales = 0;
        return payment;
    }

    //添加总销售额显示
    @Override
    public String toString() {
        String result = super.toString();
        result += "\n总销售额："+totalSales;
        return result;
    }
}
