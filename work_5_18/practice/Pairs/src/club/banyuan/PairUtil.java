package club.banyuan;

public class PairUtil {

    public static <T> Pair<T> swap(Pair<T> pair){
        pair.swap();
        return pair;
    }

    public static <T extends Measurable> Pair<T> minmax(T[] measurable){
        T min = measurable[0];
        T max = measurable[0];
        for (T t : measurable) {
            if(t.getMeasure() > max.getMeasure()){
                max = t;
            }
            if(t.getMeasure() < min.getMeasure()){
                min = t;
            }
        }
        return new Pair<>(max, min);
    }

    public static <T extends Comparable<T>> Pair<T> minmax(T[] measurable){
        T min = measurable[0];
        T max = measurable[0];
        for (T t : measurable) {
            if(t.compareTo(max) > 0){
                max = t;
            }
            if(t.compareTo(min) < 0){
                min = t;
            }
        }
        return new Pair<>(max, min);
    }
}
