package club.banyuan;

import java.util.Scanner;

class Player{
    private String name;
    private static int num =0;
    
    public Player(){
        num++;
        name = "玩家"+num;
    }

    public int guess(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的猜拳结果\n1. 剪刀\n2. 石头\n3. 布");
        int result = sc.nextInt();
        return result;
    }

    public String getName(){
        return name;
    }
    


}