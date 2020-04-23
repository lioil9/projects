package club.banyuan.fraction;

public class Fraction{
    public int num;
    public int den;
    
    /**
     * 构造方法，赋予属性
     */
    public Fraction(int num, int den){
        this.num = num;
        this.den = den;
    }
    /**
     * 将一个分数转换为两个部分;
     * 分别为整数和小数部分，先将分母整整除分子得到整数
     * 在取余数保存为double，然后再除以分子得到小数部分
     * @return
     */
    public double toDouble(){
        double a = num / den;
        double b = num%den;
        double c = b/den;
        return a+c;
    }

    /**
     * 先保存当前的分子分母
     * 然后两分母相乘得到分母
     * 分子分母互相乘的和得到分子
     */
    public Fraction plus(Fraction r){
        int n = this.num;
        int d = this.den;
        this.num = (n*r.den)+(d*r.num);
        this.den = d*r.den;
        return this;
    }
    
    /**
     * 保存当前的分子分母
     * 然后将两个分数分子分母互相乘
     * @param r
     * @return
     */
    public Fraction multiply(Fraction r){
        int n = this.num;
        int d = this.den;
        this.num = n*r.num;
        this.den = d*r.den;
        return this;
    }

    /**
     * 打印分数
     * 当分子余分母为0时输出整数
     * 当然后找到分子分母的最大公因数，没有时返回为1
     * 然后除以找到的因数除以返回
     */
    public void print(){
        if(num%den == 0){
            System.out.println(num/den);
        }else{
            int t = 1;
            for(int i=2; i<den; i++){
                if((den%i==0) && (num%i==0)){
                    t = i;
                }
            }
            System.out.println(num/t+"/"+den/t);
        }
        
    }

}
