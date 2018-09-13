package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class UniquePathTest {
    private int input1;
    private int input2;
    private int output;
    private UniquePath uniquePath;

    public UniquePathTest(int input1, int input2, int output) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Before
    public void setup() {
        uniquePath = new UniquePath();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int caseA1 = 2;
        int caseA2 = 3;
        int resA = 3;
        int caseB1 = 3;
        int caseB2 = 7;
        int resB = 28;
        return Arrays.asList(new Object[][]{
                {caseA1, caseA2, resA},
                {caseB1, caseB2, resB}
        });
    }

    @Test
    public void solutionTest() {
        int res = uniquePath.uniquePaths(input1, input2);
        Assert.assertEquals(output, res);
    }
}
