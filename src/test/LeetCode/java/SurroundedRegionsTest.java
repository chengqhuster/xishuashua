package LeetCode.java;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class SurroundedRegionsTest {
    private char[][] input;
    private SurroundedRegions surroundedRegions;

    public SurroundedRegionsTest(char[][] input) {
        this.input = input;
    }

    @Before
    public void setup(){
        surroundedRegions = new SurroundedRegions();
    }

    @Parameterized.Parameters
    public static List getParams() {
        char[][] caseA = new char[][]{
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'}
        };
        char[][] caseB = new char[][]{
                {'x', 'x', 'o', 'x'},
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'}
        };
        char[][] caseC = new char[][]{
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'},
        };
        return Arrays.asList(new Object[][]{
                {caseA},
                {caseB},
                {caseC}
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
        surroundedRegions.solve(input);
        for(char[] row : input) {
            for(char c : row) {
                System.out.print(c + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
