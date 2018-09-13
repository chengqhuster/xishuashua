package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class UniquePathIITest {
    private int[][] input;
    private int output;
    private UniquePathII uniquePathII;

    public UniquePathIITest(int[][] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        uniquePathII = new UniquePathII();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[][] caseA = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        int resA = 2;
        int[][] caseB = new int[][]{
                {0,0,0,1},
                {0,0,1,0},
                {0,0,0,0},
                {0,1,0,0}
        };
        int resB = 6;
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void solutionTest() {
        int res = uniquePathII.uniquePathsWithObstacles(input);
        Assert.assertEquals(output, res);
    }
}