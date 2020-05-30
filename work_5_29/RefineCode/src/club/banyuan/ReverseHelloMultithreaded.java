package club.banyuan;

public class ReverseHelloMultithreaded {
    public static void doReverseHello() {
        Reverse r = new Reverse(50);
        r.start();
    }
}

class Reverse extends Thread{
    private int num;

    public Reverse(int num){
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("Hello from thread "+num);
        if(num > 1) {
            Reverse r = new Reverse(--num);
            r.start();
        }
    }
}