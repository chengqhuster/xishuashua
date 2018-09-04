package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class NumberOfIslandsTest {
    private char[][] input;
    private int output;
    private NumberOfIslands numberOfIslands;

    public NumberOfIslandsTest(char[][] input, int output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setup() {
        numberOfIslands = new NumberOfIslands();
    }

    @Parameterized.Parameters
    public static List getParams() {
        char[][] caseA = new char[][] {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int resA = 1;
        char[][] caseB = new char[][] {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int resB = 3;
        return Arrays.asList(new Object[][] {
                {caseA, resA},
                {caseB, resB}
        });
    }

    @Test
    public void solutionTest(){
        for(char[] row : input) {
            for(char c : row) {
                System.out.print(c + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        int res = numberOfIslands.numIslands(input);
        for(char[] row : input) {
            for(char c : row) {
                System.out.print(c + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.println("result: " + res);
        Assert.assertEquals(output, res);
    }
}
