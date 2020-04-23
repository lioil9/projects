package club.banyuan;

import club.banyuan.fraction.Fraction;

class Main{
    public static void main(String[] args) {
        Fraction f = new Fraction(4,2);
        Fraction r = new Fraction(1,3);
        f.print();
        r.print();
        System.out.println(r.toDouble());
        f.plus(r);
        f.print();
        f.multiply(r);
        f.print();
        
    }
}