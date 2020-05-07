package club.banyuan;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int OBJ_TOTAL = 10;            //要生成对象总数
    private static final String RMC = "ReadyMadeCake";  //ReadyMadeCake类名称
    private static final int OC_PRICE = 10;             //OrderCake蛋糕单价
    private static final int RMC_PRICE = 5;             //ReadyMadeCake蛋糕单价
    private static final int WEIGHT_RANGE = 10;         //随机重量范围
    private static final int QUANTITY_RANGE = 10;       //随机数量范围


    public static void main(String[] args){
        List<Cake> cakes = new ArrayList<Cake>();

        /**
         * 随机生成要添加OrderCake和ReadyMadeCake的对象数量
         * 再随机生成蛋糕重量和数量
         */
        int r = (int)(Math.random()*OBJ_TOTAL);
        for(int i=0; i<10; i++){
            if(r-i > 0) {
                int rWeight = (int)(Math.random()*WEIGHT_RANGE+1);
                cakes.add(new OrderCake(i, OC_PRICE, rWeight));
            }else{
                int rQuantity = (int)(Math.random()*QUANTITY_RANGE+1);
                cakes.add(new ReadyMadeCake(i, RMC_PRICE, rQuantity));
            }
        }

        /**
         * 计算cakes数组中所有蛋糕的价格以及价格最高的蛋糕
         */
        double sum = 0;
        double topPrice = 0;
        int countId = 0;
        for(Cake cake : cakes){
            if(topPrice < cake.calcPrice()) {
                topPrice = cake.calcPrice();
                countId = cake.id;
            }
            sum += cake.calcPrice();
            System.out.println(cake.toString());
        }

        /**
         * 计算ReadyMadeCake蛋糕的总数和总价
         */
        double sumPrice = 0;
        int sumQuantity = 0;
        for(Cake cake : cakes){
            if(cake.getClass().toString().contains(RMC)) {
                ReadyMadeCake cake1 = (ReadyMadeCake) cake;
                sumPrice += cake.calcPrice();
                sumQuantity += cake1.getQuantity();
            }
        }

        System.out.println("总价为："+sum+"\n最高价为id为"+countId+"的蛋糕，价格为："+topPrice);
        System.out.println("ReadyMadeCake蛋糕的总价:"+sumPrice+",数量之和:"+sumQuantity);



    }
}
