package club.banyuan;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void getTruckPriceList() {
        List<String> target = new ArrayList<>();
        target.add("red pickup truck $5000");
        target.add("red pickup truck $5,000");
        target.add("red pickup truck $1,234.56");
        target.add("red pick-up truck $5000");
        target.add("red pick up truck $5000");
        target.add("red toyota pick-up truck $5000");
        target.add("red toyota 1993 pick-up truck $5000");
        target.add("blah blah red toyota 1993 pick-up truck blah blah $5000 blah");
        target.add("pickup truck red $5000");
        target.add("pick-up truck 1993 toyota red $5000");
        target.add("desperate: red 1993 toyota pickup truck for sale. $2,000 o.b.o.");
        //匹配不成功的
        target.add("pickup truck $5000");
        target.add("blue pickup truck $5000");
        target.add("red 1993 toyota automatic pick-up truck $5000");
        target.add("reddy for sale pickup truck: $5000)");
        target.add("pick-up trucks by fred: $5000");
        target.add("fred's pick-up truck sold for $5000");
        
        List<Integer> test = new ArrayList<>();
        test.add(5000);
        test.add(5000);
        test.add(1234);
        test.add(5000);
        test.add(5000);
        test.add(5000);
        test.add(5000);
        test.add(5000);
        test.add(5000);
        test.add(5000);
        test.add(2000);
        Assert.assertEquals(test, Main.getTruckPriceList(target));
    }
}