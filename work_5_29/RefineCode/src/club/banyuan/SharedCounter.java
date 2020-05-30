package club.banyuan;

public class SharedCounter {
    static int counter = 0;


    public static void reset() {
        counter = 0;
    }

    public static int increment(int numThreads, int numIncrementsPerThread) throws InterruptedException {
        addCounter[] ts = new addCounter[numThreads];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new addCounter(numIncrementsPerThread);
            ts[i].start();
        }

        for (addCounter t : ts) {
            t.join();
        }

        return counter;
    }
}

class addCounter extends Thread{
    private final int num;

    public addCounter(int num){
        this.num = num;
    }

    @Override
    public void run() {
        SharedCounter.counter += num;
    }
}
