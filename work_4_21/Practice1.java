class Practice1{
    public static void main(String args[]){

        int a =2;
        if(true || (++a > 1)){
            System.out.println("用||运算，输出a的值为"+a);
        }
        /*
        在用||时a输出的值为2，而在用|时b输出的值为3,
        可以看出第一种情况，++a并没有运行，只判断了左边的true值时就进行了下一步操作
        */
        int b = 2;
        if(true | (++b > 1)){
            System.out.println("用|运算，输出b的值为"+b);
        }

        float c = 3.99f;
        int d = (int)c;
        System.out.println("强制转换前"+c); 
        /*将带小数的浮点类型强制转换赋值给int型时会直接将小数部分截位，得到其整数部分*/
        System.out.println("直接强制转换后"+d);

        //如果要实现将float型强转int四舍五入可以在强转时后面加上一个 0.5f 值
        d = (int)(c+0.5f);
        System.out.println("强转四舍五入后："+d);

        
        c = 3247483647f;
        d = (int)c;
        /*在将超过int型数据范围的float类型值强转给int型变量时，
        打印输出会发现，此时int型变量为其取值范围的最大值 2147483647
        */
        System.out.println("强转前："+c);  //科学计数法打印
        System.out.println("强转前："+String.format("%.0f", c));  //保留零位小数打印
        System.out.println("强转后："+d);


    }
}