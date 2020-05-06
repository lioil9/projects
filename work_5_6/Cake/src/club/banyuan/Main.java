package club.banyuan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Cake> cakes = new ArrayList<Cake>();

        int r = (int)(Math.random()*10);
        System.out.println(r);
        for(int i=0; i<10; i++){
            if(r-i > 0) {
                int rWeight = (int)(Math.random()*10+1);
                cakes.add(new OrderCake(i, 10, rWeight));
            }else{
                int rQuantity = (int)(Math.random()*10+1);
                cakes.add(new ReadyMadeCake(i, 5, rQuantity));
            }
        }

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

        double sumPrice = 0;
        int sumQuantity = 0;
        for(Cake cake : cakes){
            if(cake.getClass().toString().contains("ReadyMadeCake")) {
                ReadyMadeCake cake1 = (ReadyMadeCake) cake;
                sumPrice += cake.calcPrice();
                sumQuantity += cake1.getQuantity();
            }
        }
        System.out.println("总价为："+sum+"\n最高价为id为"+countId+"的蛋糕，价格为："+topPrice);
        System.out.println("ReadyMadeCake蛋糕的总价:"+sumPrice+",数量之和:"+sumQuantity);



    }
}
