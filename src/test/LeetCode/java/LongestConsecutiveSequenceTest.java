package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LongestConsecutiveSequenceTest {
    private LongestConsecutiveSequence longestConsecutiveSequence;
    private int[] input;
    private int output;

    public LongestConsecutiveSequenceTest(int[] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        longestConsecutiveSequence = new LongestConsecutiveSequence();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[] caseA = new int[]{100, 4, 200, 1, 3, 2};
        int resA = 4;
        return Arrays.asList(new Object[][] {
                {caseA, resA}
        });
    }

    @Test
    public void solutionTest() {
        int res = longestConsecutiveSequence.longestConsecutive(input);
        System.out.println("input:  " + Arrays.toString(input));
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
