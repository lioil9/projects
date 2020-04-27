import java.util.Scanner;

class GuessNum {
    private int[] num = new int[4];
    public int countSite;
    public int countNum;

    {
        int[] array = {1,2,3,4,5,6,7,8,9,0};
        int index = 0, ranLen=10, len = 9;
        for (int i = 0; i < 4; i++) {
            index = (int)(Math.random()*ranLen);
            num[i] = array[index];
            array[index] = array[len--];
            ranLen--;
        }
    }
    
    public void printNum(){
        for(int a : num)
            System.out.print(a);
        System.out.println();
    }

    public void guess(int n){
        countSite = 0;
        countNum = 0;
        int[] na = new int[4];
        int temp = 1000;
        for(int i=0; i<4; i++){
            na[i] = n / temp;
            n = n - na[i]*temp;
            temp /= 10;
        }
        for(int i=0; i<4; i++){
            if(na[i] == num[i]){
                countSite++;
            }
            for(int j=0; j<4; j++){
                if(na[i] == num[j])
                    countNum++;
            }
        }
    }

    public void printGuess(){
        boolean flag = true;
        do{
            System.out.println("请输入4个数字：");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            guess(n);
            if(countNum == 4 && countSite ==4){
                System.out.println("回答正确");
                sc.close();
                flag = false;
            }else{
                System.out.println("包含了"+countNum+"个正确的数字");
                if(countSite == 0)
                    System.out.println("这些数字位置错误");
                else
                    System.out.println(countSite+"个数字位置正确");
            }
        }while(flag);

    }

    public static void main(String[] args) {
        GuessNum gNum = new GuessNum();
        gNum.printNum();
        System.out.print("猜一猜4个不重复的数字");
        gNum.printGuess();
    }
}