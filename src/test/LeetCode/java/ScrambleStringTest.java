package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ScrambleStringTest {
    private String input1;
    private String input2;
    private boolean output;
    private ScrambleString scrambleString;

    public ScrambleStringTest(String input1, String input2, boolean output) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Before
    public void setup() {
        scrambleString = new ScrambleString();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA1 = "great";
        String caseA2 = "rgtae";
        boolean resA = true;
        String caseB1 = "abcde";
        String caseB2 = "caebd";
        boolean resB = false;
        return Arrays.asList(new Object[][]{
                {caseA1, caseA2, resA},
                {caseB1, caseB2, resB}
        });
    }

    @Test
    public void solutionTest() {
        System.out.println("input 1 :" + input1);
        System.out.println("input 2 :" + input2);
        boolean res = scrambleString.isScramble(input1, input2);
        System.out.println("result  :" + res);
        Assert.assertEquals(output, res);
    }
}
