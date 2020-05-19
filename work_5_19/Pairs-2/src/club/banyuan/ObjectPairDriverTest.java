package club.banyuan;

import org.junit.Assert;


public class ObjectPairDriverTest {

    @org.junit.Test
    public void largestStadium() {
        ObjectPair[] stadiums = new ObjectPair[3];
        stadiums[0] = new ObjectPair("五棵松", 25000);
        stadiums[1] = new ObjectPair("鸟巢", 109901);
        stadiums[2] = new ObjectPair("奥体中心", "66,233");

        System.out.println(stadiums[0]);
        System.out.println(ObjectPairDriver.largestStadium(stadiums));
        Assert.assertEquals(stadiums[1].getFirst().toString(),ObjectPairDriver.largestStadium(stadiums));
    }
}