package LeetCode.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class FrogJumpTest {

    private int[] input;

    private FrogJump frogJump;

    @Parameters
    public static List getParams() {
        int[] caseA = {0,1,3,5,6,8,12,17};
        int[] caseB = {0,1,2,3,4,8,9,11};
        int[] caseC = {0,2};
        return Arrays.asList(caseA , caseB, caseC);
    }

    public FrogJumpTest(int[] input) {
        this.input = input;
    }

    @Before
    public void setUp() {
        frogJump = new FrogJump();
    }

    @Test
    public void SolutionTest() {
        System.out.println("input:  " + Arrays.toString(input));
        System.out.println("result: " + frogJump.canCross(input));
    }
}
