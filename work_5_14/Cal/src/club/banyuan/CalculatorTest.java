package club.banyuan;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void compute() throws CalculatorException {
        String[] test = {"34",  "/", "2"};
        assertEquals(17,Calculator.compute(test));
    }

    @Test
    public void parseAndCompute() {
    }
}