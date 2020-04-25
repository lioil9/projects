
public class Random {

    int randomInt(int from, int to){
        int r = (int)(Math.random()*(from-to)) + to;
        return r;
    }
    public static void main(String[] args) {
        Random rd = new Random();

        System.out.println(rd.randomInt(-10,5));
    }
}