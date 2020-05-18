package club.banyuan;

import org.junit.Assert;

public class PairUtilTest {

    @org.junit.Test
    public void swap() {
        Pair<Integer> pair = new Pair<>(5, 10);
        PairUtil.swap(pair);
        Assert.assertEquals(pair.getFirst(), 10);
        Assert.assertEquals(pair.getSecond(), 5);
    }

    @org.junit.Test
    public void test() {
        Country[] countries = new Country[]{new Country(1000), new Country(800), new Country(100), new Country(10000)};
        Pair<Country> pair = PairUtil.minmax(countries);
        Assert.assertEquals(pair.getFirst(), countries[3]);
        Assert.assertEquals(pair.getSecond(), countries[2]);
    }

    @org.junit.Test
    public void testMinmax() {
        Integer[] list = new Integer[]{1, 5, 7, 10, 50, 55, 0};
        Pair<Integer> pair = PairUtil.minmax(list);
        Assert.assertEquals(pair.getFirst(), 55);
        Assert.assertEquals(pair.getSecond(), 0);
    }
}