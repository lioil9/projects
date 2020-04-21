import java.util.Scanner;

public class Practice3 {
    /**
     * 定义一个绝对值方法
     * @param n
     * @return
     */
    static int abs(int n){
        if(n<0)
            return -n;
        else
            return n;
    }
    /**
     * 不使用*来进行两个数的相乘
     * @param n1
     * @param n2
     * @return
     */
    static int multiply(int n1, int n2){
        int n = 0;
        if(n1==0 || n2==0){
            return n;
        }
        for(int i=0; i<abs(n1); i++){
            n += abs(n2);
        }
        if((n1<0 && n2<0) || (n1>0 && n2>0))
            return n;
        else
            return -n;
    }
    /**
     * 将输入的分钟转换成多少年和多少天的格式输出
     * @param minute
     */
    static void convert(long minute) {
        // 首先利用整型数据除以每年的分钟数舍去小数得到年数
        int year = (int) (minute / (60 * 24 * 365));
        // 再用余数得到去掉整年数的分钟后再除以每天的的分钟得到剩下天数
        int day = (int) (minute % (60 * 24 * 365) / (60 * 24));
        System.out.println(minute+" 分钟是"+year + "年" + day + "天");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入要进行转换的分钟：");
        long minute = sc.nextLong();
        convert(minute);
        convert(3456789);
        System.out.println(multiply(-90,4));

    }

}