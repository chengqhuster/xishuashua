package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class RedundantConnectionIITest {
    private int[][] input;
    private int[] output;
    RedundantConnectionII redundantConnectionII;

    public RedundantConnectionIITest(int[][] input, int[] output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        redundantConnectionII = new RedundantConnectionII();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[][] caseA = {{1,2}, {1,3}, {2,3}};
        int[] resA = {2, 3};
        int[][] caseB = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        int[] resB = {4, 1};
        int[][] caseC = {{2,1}, {3,1}, {4,2}, {1,4}};
        int[] resC = {2, 1};
        int[][] caseD = {{5,2}, {5,1}, {3,1}, {3,4}, {3,5}};
        int[] resD = {3, 1};
        return Arrays.asList(new Object[][] {
                {caseA, resA},
                {caseB, resB},
                {caseC, resC},
                {caseD, resD}
        });
    }

    @Test
    public void solutionTest() {
        System.out.print("input: ");
        for(int i=0; i<input.length; i++) {
            System.out.print(" " + Arrays.toString(input[i]));
        }
        int[] res = redundantConnectionII.findRedundantDirectedConnection(input);
        System.out.println("\nresult: " + Arrays.toString(res));
        Assert.assertArrayEquals(res, output);
    }
}
