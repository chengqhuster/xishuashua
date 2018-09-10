package ClassicProblem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ReversePairsTest {
    private int[] input;
    private int output;
    private ReversePairs reversePairs;

    public ReversePairsTest(int[] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        reversePairs = new ReversePairs();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[] caseA = new int[]{1, 5, 2, 4, 3};
        int resA = 4;
        return Arrays.asList(new Object[][]{
                {caseA, resA}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.println("input:  " + Arrays.toString(input));
        int res = reversePairs.getReversePairs(input);
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
