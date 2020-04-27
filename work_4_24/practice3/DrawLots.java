import java.util.Scanner;

class DrawLots{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入内容进行抽签");
        String s = sc.nextLine();
        while(!s.equals("0")){
            int r = (int)(Math.random()*100);
            if(r>=0 && r<5){
                System.out.println("大吉");
            }else if(r>=5 && r<15){
                System.out.println("中吉");
            }else if(r>=15 && r<30){
                System.out.println("小吉");
            }else if(r>=30 && r<60){
                System.out.println("吉");
            }else if(r>=60 && r<80){
                System.out.println("末吉");
            }else if(r>=80 && r<95){
                System.out.println("凶");
            }else if(r>=95 && r<100){
                System.out.println("大凶");
            }
            System.out.println("请输入内容进行抽签");
            s = sc.nextLine();
        }
    }
}