package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class FirstMissingPositiveTest {
    private int[] input;
    private int output;
    private FirstMissingPositive firstMissingPositive;

    public FirstMissingPositiveTest(int[] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        firstMissingPositive = new FirstMissingPositive();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[] caseA = {1,2,0};
        int resA = 3;
        int[] caseB = {3,4,-1,1};
        int resB = 2;
        int[] caseC = {7,8,9,11,12};
        int resC = 1;
        return Arrays.asList(new Object[][] {
                {caseA, resA},
                {caseB, resB},
                {caseC, resC}
        });
    }

    @Test
    public void solutionTest() {
        System.out.println("input  : " + Arrays.toString(input));
        int res = firstMissingPositive.firstMissingPositive(input);
        System.out.println("output : " + res);
        Assert.assertEquals(output, res);
    }
}
