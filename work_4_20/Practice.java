import java.util.Arrays;



class Practice {
    public static String getType(Object test) {
        return test.getClass().getName().toString();
    }
     public static void main(String[] args) {
        int time1 = 7*24*60*60*1000;
        long time2 = 30*24*60*60*1000L;

        /*第一种方法:直接赋值
        String[][] a ={{"你","我","他"},{"金","木","水","火","土"},{"天","地"}} ;
        */
        //第二种：分行赋值
        String[][] a = new String[3][];
        a[0] = new String[]{"你","我","他"};
        a[1] = new String[]{"金","木","水","火","土"};
        a[2] = new String[]{"天","地"};
        /*
        a[1].length = 5;
        当我们给数组的length赋值时会报错，显示如下
        Practice.java:16: 错误: 无法为最终变量length分配值
        */
        /*第一种方法：可以直接将小数赋值并初始化为float类型，
        float c = 3.4;
        在进行编译的时候会报错显示如下
        Practice.java:26: 错误: 不兼容的类型: 从double转换到float可能会有损失
        */
        int d = a[1].length;

        System.out.println("7天的毫秒数为"+time1);
        System.out.println("30天的毫秒数为"+time2);
        System.out.println(Arrays.toString(a[0]));
        System.out.println(Arrays.toString(a[1]));
        System.out.println(Arrays.toString(a[2]));
        System.out.println("第一行数组长度为："+a[0].length);
        System.out.println("第二行数组长度为："+a[1].length);
        System.out.println("第三行数组长度为："+a[2].length);
        /*
        第二种方法：定义一个方法用来显示变量的类型，
        在输入3.4时会打印出 java.lang.Double 表示默认为double类型
        */
        //判断数组长度的类型可以直接打印，也可以直接赋值给一个int类型的变量，赋值成功且成功打印
        System.out.println("将数组长度赋值给d后："+d);
        System.out.println("数组长度数据类型为："+getType(a[0].length));
        System.out.println("小数默认数据类型为："+getType(3.4));
    }
    
}