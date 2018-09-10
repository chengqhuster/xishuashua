package LeetCode.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class InterleavingStringTest {
    private String input1;
    private String input2;
    private String input3;
    private boolean output;
    private InterleavingString interleavingString;

    public InterleavingStringTest(String s1, String s2, String s3, boolean output) {
        this.input1 = s1;
        this.input2 = s2;
        this.input3 = s3;
        this.output = output;
    }

    @Before
    public void setup() {
        interleavingString = new InterleavingString();
    }

    @Parameterized.Parameters
    public static List getParams() {
        String caseA[] = "aabcc dbbca aadbbcbcac".split(" ");
        boolean resA = true;
        String caseB[] = "aabcc dbbca aadbbbaccc".split(" ");
        boolean resB = false;
        String caseC[] = new String[]{"", "", ""};
        boolean resC = true;
        return Arrays.asList(new Object[][]{
                {caseA[0], caseA[1], caseA[2], resA},
                {caseB[0], caseB[2], caseB[2], resB},
                {caseC[0], caseC[1], caseC[2], resC}
        });
    }

    @Test
    public void solutionTest() {
        System.out.println("String s1:" + input1);
        System.out.println("String s2:" + input2);
        System.out.println("String s3:" + input3);
        boolean res = interleavingString.isInterleave(input1, input2, input3);
        System.out.println("Result   :" + res);
        Assert.assertEquals(output, res);
    }
}
