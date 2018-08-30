package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class RedundantConnectionTest {

    private int[][] input;
    private int[] output;

    private RedundantConnection redundantConnection;

    public RedundantConnectionTest(int[][] input, int[] output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        redundantConnection = new RedundantConnection();
    }

    @Parameterized.Parameters
    public static List getParams() {
        int[][] caseA = {{1,2}, {1,3}, {2,3}};
        int[] resA = {2, 3};
        int[][] caseB = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[] resB = {1, 4};
        int[][] caseC = {{1,4},{3,4},{1,3},{1,2},{4,5}};
        int[] resC = {1, 3};
        return Arrays.asList(new Object[][]{
                {caseA, resA},
                {caseB, resB},
                {caseC, resC}
        });
    }

    @Test
    public void SolutionTest() {
        System.out.print("input: ");
        for(int i=0; i<input.length; i++) {
            System.out.print(" " + Arrays.toString(input[i]));
        }
        int[] res = redundantConnection.findRedundantConnection(input);
        System.out.println("\nresult: " + Arrays.toString(res));
        Assert.assertArrayEquals(res, output);
    }

}
